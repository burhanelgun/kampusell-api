package com.bestudios.kampusellapi.model;

import com.bestudios.kampusellapi.entity.ActivationCode;
import com.bestudios.kampusellapi.entity.University;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignUpForm {


    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @Nullable
    private ActivationCode activationCode;

    private University university;

}