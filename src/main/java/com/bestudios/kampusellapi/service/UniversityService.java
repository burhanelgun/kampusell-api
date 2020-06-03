package com.bestudios.kampusellapi.service;

import com.bestudios.kampusellapi.dto.UniversityDTO;
import com.bestudios.kampusellapi.mapper.UniversityMapper;
import com.bestudios.kampusellapi.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {
    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    UniversityMapper universityMapper;

    public List<UniversityDTO> getAllUniversities() {
        return universityMapper.entityToDTOList(universityRepository.findAll());
    }
}
