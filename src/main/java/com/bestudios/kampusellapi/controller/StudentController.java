package com.bestudios.kampusellapi.controller;

import com.bestudios.kampusellapi.model.SignInForm;
import com.bestudios.kampusellapi.model.SignUpForm;
import com.bestudios.kampusellapi.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class StudentController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateStudent(@Valid @RequestBody SignInForm loginRequest) {
        return authService.authenticateStudent(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody SignUpForm signUpRequest) {
        return authService.registerStudent(signUpRequest);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<?> deleteStudent(@Valid @RequestBody SignUpForm signUpForm) {
        return authService.deleteStudent(signUpForm);
    }


}
