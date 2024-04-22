package com.example.oscar.dtos;

import com.example.oscar.models.Indicavel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record IndicacaoRecordDto(@NotNull Indicavel indicavel, @NotBlank String categoria) {
}