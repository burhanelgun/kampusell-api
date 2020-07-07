package com.bestudios.kampusellapi.service;

import com.bestudios.kampusellapi.entity.ActivationCode;
import com.bestudios.kampusellapi.entity.Role;
import com.bestudios.kampusellapi.entity.Student;
import com.bestudios.kampusellapi.entity.University;
import com.bestudios.kampusellapi.model.*;
import com.bestudios.kampusellapi.repository.ActivationCodeRepository;
import com.bestudios.kampusellapi.repository.RoleRepository;
import com.bestudios.kampusellapi.repository.StudentRepository;
import com.bestudios.kampusellapi.repository.UniversityRepository;
import com.bestudios.kampusellapi.security.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentRepository userDAO;


    @Autowired
    private ActivationCodeRepository activationCodeRepository;


    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UniversityRepository universityRepository;


    @Autowired
    EmailService emailService;


    public ResponseEntity<?> authenticateStudent(@Valid @RequestBody SignInForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        log.info("Student is Signed in with LoginForm:" + loginRequest);
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    public ResponseEntity<?> registerStudent(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userDAO.existsByUsername(signUpRequest.getUsername())) {
            log.error("Fail -> Username is already taken!");
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }


        // Creating student's account
        Student student = new Student(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Student Role not find."));
                    roles.add(adminRole);

                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Student Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Student Role not find."));
                    roles.add(userRole);
            }
        });

        student.setRoles(roles);

        Optional<University> university = universityRepository.findById(signUpRequest.getUniversity().getId());
        student.setUniversity(university.get());


        ActivationCode activationCode = new ActivationCode();
        activationCodeRepository.save(activationCode);
        student.setActivationCode(activationCode);


        userDAO.save(student);

        emailService.sendMail(student.getEmail(), "Kampusell Aktivasyon Kodu", activationCode.getActivationCode());


        log.info("Student is Signed up with SignUpForm:" + signUpRequest + " with Roles:" + roles);
        return new ResponseEntity<>(new ResponseMessage(activationCode.getActivationCode().toString()), HttpStatus.OK);
    }


    public ResponseEntity<?> deleteStudent(SignUpForm signUpForm) {
        Optional<Student> user = userDAO.findByUsername(signUpForm.getUsername());
        userDAO.delete(user.get());
        Optional<ActivationCode> activationCodeOpt = activationCodeRepository.findByActivationCode(signUpForm.getActivationCode().getActivationCode());
        activationCodeRepository.delete(activationCodeOpt.get());
        return new ResponseEntity<>(new ResponseMessage("Student deleted successfully!"), HttpStatus.OK);

    }

    public ResponseEntity<SignUpForm> getUserInfos() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<Student> student = studentRepository.findByUsername(username);
        Student student1 = student.get();
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail(student1.getEmail());
        signUpForm.setUsername(student1.getUsername());
        signUpForm.setUniversity(student1.getUniversity());
        return new ResponseEntity<SignUpForm>(signUpForm, HttpStatus.OK);
    }
}
