package com.example.closed_club.dto;
import java.time.LocalDateTime;

public record ErrorR(int status, String message, LocalDateTime timestamp) {
}