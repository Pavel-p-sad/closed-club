package com.example.closed_club;
import com.example.closed_club.dto.QrCodeDto;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.entity.QrCode;
import com.example.closed_club.exception.NotFoundException;
import com.example.closed_club.repository.ParticipantRepository;
import com.example.closed_club.repository.QrCodeRepository;
import com.example.closed_club.service.QrCodeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QrCodeServiceTest {

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private QrCodeRepository qrCodeRepository;

    @InjectMocks
    private QrCodeService qrCodeService;
    @Test
    void createShouldCreateQrCodeForExistingParticipant() {
        Participant p = new Participant();
        p.setId(1L);
        p.setName("йй");
        QrCode sQr = new QrCode();
        sQr.setId(10L);
        sQr.setUuid(UUID.randomUUID());
        sQr.setDateTime(LocalDateTime.now());
        sQr.setParticipant(p);

        when(participantRepository.findById(1L)).thenReturn(Optional.of(p));
        when(qrCodeRepository.save(any(QrCode.class))).thenReturn(sQr);
        QrCodeDto result = qrCodeService.create(1L);

        assertNotNull(result);
        assertEquals(10L, result.id());
        assertEquals(p.getId(), result.ParticipantId());
        verify(qrCodeRepository, times(1)).save(any(QrCode.class));
    }
    @Test
    void createShouldThrowNotFoundException_whenParticipantNotExists() {

        when(participantRepository.findById(990L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> qrCodeService.create(990L));
        verify(qrCodeRepository, never()).save(any());
    }
}