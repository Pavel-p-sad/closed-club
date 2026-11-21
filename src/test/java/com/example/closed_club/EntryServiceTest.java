package com.example.closed_club;
import com.example.closed_club.dto.ParticipantDto;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.entity.QrCode;
import com.example.closed_club.exception.NotFoundException;
import com.example.closed_club.repository.QrCodeRepository;
import com.example.closed_club.service.EntryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntryServiceTest {
    @Mock
    private QrCodeRepository qrCodeRepository;
    @InjectMocks
    private EntryService entryService;

    @Test
    void checkEntry_shouldReturnParticipant_whenUuidExists() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        Participant participant = new Participant("Иван", "Иванов", "ййй");
        participant.setId(1L);

        QrCode qrCode = new QrCode(uuid, LocalDateTime.now(), participant);

        when(qrCodeRepository.findByUuid(uuid)).thenReturn(Optional.of(qrCode));
        ParticipantDto result = entryService.checkEntry(uuid);

        assertEquals(participant.getId(), result.id());
        verify(qrCodeRepository, times(1)).save(qrCode);
    }

    @Test
    void checkEntry_shouldThrowNotFoundException_whenUuidNotFound() {
        UUID uuid = UUID.randomUUID();
        when(qrCodeRepository.findByUuid(uuid)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> entryService.checkEntry(uuid));
    }
}