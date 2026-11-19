package com.example.closed_club.dto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ErrorR(
        @Min(value = 100, message = "Status должен быть >= 100")
        @Max(value = 599, message = "Status должен быть <= 599")
        int status,
        @NotBlank(message = "Message не может быть пустым")
        String message,
        @NotNull(message = "Timestamp не может быть null")
        LocalDateTime timestamp) {}
