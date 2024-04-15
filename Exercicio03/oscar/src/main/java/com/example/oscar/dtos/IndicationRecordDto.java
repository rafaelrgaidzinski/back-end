package com.example.oscar.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record IndicationRecordDto(@NotNull UUID eligibleId, @NotBlank String category) {
}