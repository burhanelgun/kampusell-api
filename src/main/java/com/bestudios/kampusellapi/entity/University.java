package com.bestudios.kampusellapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "university", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name",
                "email"
        }),
})
@Data
@NoArgsConstructor
public class University {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = true, length = 255)
    private String name;

    @Column(name = "email", nullable = true, length = 255)
    private String email;

}
