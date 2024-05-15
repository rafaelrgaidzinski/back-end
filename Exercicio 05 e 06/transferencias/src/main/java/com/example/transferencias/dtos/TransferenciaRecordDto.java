package com.example.transferencias.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record TransferenciaRecordDto(  @NotNull Double valor,
                                       @NotNull Date dataTransferencia,
                                       @NotBlank String contaOrigem,
                                       @NotBlank String nomeBeneficiario,
                                       @NotBlank String cpf,
                                       @NotBlank String Banco,
                                       @NotBlank String Agencia,
                                       @NotBlank String contaDestino) {
}
