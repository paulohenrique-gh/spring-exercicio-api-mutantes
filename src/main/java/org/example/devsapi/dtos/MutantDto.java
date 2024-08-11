package org.example.devsapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MutantDto(
        @NotBlank String password,
        @NotBlank String name,
        @NotBlank String power,
        @NotNull Integer age,
        @NotNull Integer enemiesDefeated
) {}
