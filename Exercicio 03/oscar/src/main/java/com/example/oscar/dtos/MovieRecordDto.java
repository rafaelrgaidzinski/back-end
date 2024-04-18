package com.example.oscar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieRecordDto(@NotNull Boolean eligible, @NotNull Short numberOfIndications, @NotBlank String name, @NotBlank String gender) {
}