package org.example.devsapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "mutants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mutant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String power;

    @NotNull
    private Integer age;

    @NotNull
    private Integer aliensDefeated;

    @NotNull
    private Integer demonsDefeated;

    private Boolean isEligibleForEspada = false;

    private Boolean isInSchool = false;
}
