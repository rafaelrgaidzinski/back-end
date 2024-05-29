package com.example.transacoes.dtos.clienteDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteRequestDto(@NotBlank(message = "É obrigatório informar o nome do cliente")
                                String nome,
                                @NotNull(message = "O saldo não pode ser nulo")
                                Double saldo,
                                @NotBlank(message = "É obrigatório informar a senha")
                                @Size(min = 8, max = 20, message = "A senha deve ter no minimo 8 e no máximo 20 caracteres")
                                String senha) {
}