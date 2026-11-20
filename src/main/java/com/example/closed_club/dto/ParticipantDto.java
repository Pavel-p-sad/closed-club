package com.example.closed_club.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ParticipantDto(

        Long id,
        @NotBlank(message = "Имя не может быть пустым")
        @Size(max = 100, message = "Имя не длиннее 100 символов")
        String name,
        @NotBlank(message = "Имя не может быть пустым")
        @Size(max = 100, message = "Имя не длиннее 100 символов")
        String lastName,
        @NotBlank(message = "Имя не может быть пустым")
        @Size(max = 100, message = "Имя не длиннее 100 символов")
        String postName
) {}