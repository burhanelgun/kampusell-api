package com.bestudios.kampusellapi.repository;

import com.bestudios.kampusellapi.entity.ActivationCode;
import com.bestudios.kampusellapi.entity.Category;
import com.bestudios.kampusellapi.entity.Role;
import com.bestudios.kampusellapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivationCodeRepository  extends JpaRepository<ActivationCode, Long> {
    Optional<ActivationCode> findByActivationCode(String activationCode);

}
