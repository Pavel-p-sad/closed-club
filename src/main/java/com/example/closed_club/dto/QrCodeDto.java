package com.example.closed_club.dto;
import java.time.LocalDateTime;
import java.util.UUID;

public record QrCodeDto(Long id, UUID uuid, LocalDateTime dateTime, Long ParticipantId) {
}

