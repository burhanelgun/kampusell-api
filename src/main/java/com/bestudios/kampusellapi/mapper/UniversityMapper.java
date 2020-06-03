package com.bestudios.kampusellapi.mapper;

import com.bestudios.kampusellapi.dto.UniversityDTO;
import com.bestudios.kampusellapi.entity.University;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityMapper {

    public UniversityDTO entityToDTO(University university) {
        UniversityDTO universityDTO = new UniversityDTO();
        universityDTO.setId(university.getId());
        universityDTO.setName(university.getName());
        universityDTO.setEmail(university.getEmail());
        return universityDTO;
    }

    public List<UniversityDTO> entityToDTOList(List<University> universities) {
        List<UniversityDTO> universityDTOS = new ArrayList<UniversityDTO>();
        for (University university : universities) {
            universityDTOS.add(entityToDTO(university));
        }
        return universityDTOS;
    }

}
