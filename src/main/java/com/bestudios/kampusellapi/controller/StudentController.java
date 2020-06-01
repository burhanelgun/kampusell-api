package com.bestudios.kampusellapi.controller;

import com.bestudios.kampusellapi.dto.StudentDTO;
import com.bestudios.kampusellapi.entity.Role;
import com.bestudios.kampusellapi.entity.Student;
import com.bestudios.kampusellapi.model.*;
import com.bestudios.kampusellapi.repository.RoleRepository;
import com.bestudios.kampusellapi.repository.StudentRepository;
import com.bestudios.kampusellapi.security.jwt.JwtProvider;
import com.bestudios.kampusellapi.service.AuthService;
import com.bestudios.kampusellapi.service.StudentService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class StudentController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInForm loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody SignUpForm signUpForm) {
        return authService.deleteUser(signUpForm);
    }



}
