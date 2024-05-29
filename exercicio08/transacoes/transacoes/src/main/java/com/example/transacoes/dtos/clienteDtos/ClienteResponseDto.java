package com.example.transacoes.dtos.clienteDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteResponseDto(@NotBlank(message = "É obrigatório informar o nome do cliente")
                                String nome,
                                 @NotNull(message = "O saldo não pode ser nulo")
                                Double saldo) {
}