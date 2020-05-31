package com.bestudios.kampusellapi.service;

import com.bestudios.kampusellapi.entity.Role;
import com.bestudios.kampusellapi.entity.Student;
import com.bestudios.kampusellapi.model.*;
import com.bestudios.kampusellapi.repository.RoleRepository;
import com.bestudios.kampusellapi.repository.StudentRepository;
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
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        log.info("User is Signed in with LoginForm:" + loginRequest);
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userDAO.existsByUsername(signUpRequest.getUsername())) {
            log.error("Fail -> Username is already taken!");
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }


        // Creating user's account
        Student user = new Student(signUpRequest.getUsername(),signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userDAO.save(user);

        log.info("User is Signed up with SignUpForm:" + signUpRequest + " with Roles:" + roles);
        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }


    public ResponseEntity<?> deleteUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<Student> student = userDAO.findByUsername(username);
        userDAO.delete(student.get());
        return new ResponseEntity<>(new ResponseMessage("User deleted successfully!"), HttpStatus.OK);

    }
}
