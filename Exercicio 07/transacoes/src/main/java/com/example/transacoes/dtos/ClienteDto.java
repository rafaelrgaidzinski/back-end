package com.example.transacoes.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteDto(@NotBlank String nome,
                         @NotNull Double saldo,
                         @NotBlank String senha) {
}
