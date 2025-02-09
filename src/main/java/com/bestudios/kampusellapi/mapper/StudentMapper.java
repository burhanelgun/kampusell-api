package com.bestudios.kampusellapi.mapper;

import com.bestudios.kampusellapi.dto.StudentDTO;
import com.bestudios.kampusellapi.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    @Autowired
    ProductMapper productMapper;

    StudentDTO entityToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setUsername(student.getUsername());
        studentDTO.setUniversityName(student.getUniversity().getName());
        studentDTO.setEmail(student.getEmail());

        return studentDTO;
    }


}
