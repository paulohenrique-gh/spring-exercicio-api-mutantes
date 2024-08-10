package org.example.devsapi.dtos;

import jakarta.validation.constraints.NotNull;

public record MutantDto(
        @NotNull String password,
        @NotNull String name,
        @NotNull String power,
        @NotNull Integer age,
        @NotNull Integer enemiesDefeated
) {}
