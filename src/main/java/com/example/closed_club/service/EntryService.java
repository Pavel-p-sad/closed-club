package com.example.closed_club.service;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.entity.QrCode;
import com.example.closed_club.exception.NotFoundException;
import com.example.closed_club.repository.ParticipantRepository;
import com.example.closed_club.repository.QrCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EntryService {
    private final ParticipantRepository participantRepository;
    private final QrCodeRepository qrCodeRepository;

    public Participant checkEntry(UUID uuid) {
        QrCode qrCode = qrCodeRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Вход запрещён: код не найден"));
        Participant participant = qrCode.getParticipant();
        qrCode.setUuid(UUID.randomUUID());
        qrCodeRepository.save(qrCode);
        return participant;
    }
}

