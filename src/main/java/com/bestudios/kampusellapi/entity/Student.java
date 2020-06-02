package com.bestudios.kampusellapi.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@NoArgsConstructor
public class Student {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    @Getter
    @Setter
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Getter
    @Setter
    private String username;

    @NotBlank
    @Size(min = 6, max = 100)
    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    @NotBlank
    @Column(name = "email", nullable = true, length = 255)
    private String email;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Getter
    @Setter
    private Set<Role> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_university",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "university_id"))
    @Getter
    @Setter
    private University university;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_activation_code",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "activation_code_id"))
    @Getter
    @Setter
    private ActivationCode activationCode;


    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "student")
    @Null
    private List<Product> products;


    public Student(String username,String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;

    }


}