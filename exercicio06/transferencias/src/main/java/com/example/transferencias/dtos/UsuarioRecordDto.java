package com.example.transferencias.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UsuarioRecordDto(@NotBlank(message = "") String nome,
                               @NotBlank String cpf,
                               @NotNull Date dataNascimento,
                               @NotBlank String email,
                               @NotBlank String tipoConta,
                               @NotBlank String senha) {
}
