package com.bestudios.kampusellapi.repository;

import com.bestudios.kampusellapi.entity.ActivationCode;
import com.bestudios.kampusellapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationCodeRepository  extends JpaRepository<ActivationCode, Long> {
}
