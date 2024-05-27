package com.example.transacoes.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransacaoDto(@NotBlank(message = "É obrigatório informar o nome do recebedor")
                           String recebedor,
                           @NotBlank(message = "É obrigatório informar o nome do pagador")
                           String pagador,
                           @NotNull(message = "O valor não pode ser nulo")
                           @Min(value = 1, message = "O valor deve ser maior do que zero")
                           Double valor) {
}
