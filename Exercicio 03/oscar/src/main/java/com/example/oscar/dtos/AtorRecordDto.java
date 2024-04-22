package com.example.oscar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtorRecordDto(@NotNull Boolean elegivel, @NotNull Short numeroDeIndicacoes, @NotBlank String nomeAtor, @NotBlank String nacionalidade) {
}
