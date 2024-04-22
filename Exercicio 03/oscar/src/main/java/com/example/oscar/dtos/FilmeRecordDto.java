package com.example.oscar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FilmeRecordDto(@NotNull Boolean elegivel, @NotNull Short numeroDeIndicacoes, @NotBlank String nomeFilme, @NotBlank String generoFilme) {
}