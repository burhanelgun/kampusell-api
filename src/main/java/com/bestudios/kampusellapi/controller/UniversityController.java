package com.bestudios.kampusellapi.controller;

import com.bestudios.kampusellapi.dto.UniversityDTO;
import com.bestudios.kampusellapi.service.UniversityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class UniversityController {

    @Autowired
    private UniversityService universityService;


    @GetMapping("/universities")
    public List<UniversityDTO> getAllUniversities() {
        List<UniversityDTO> universityDTOS = universityService.getAllUniversities();
        return universityDTOS;
    }

}
