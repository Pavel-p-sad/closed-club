package com.example.closed_club.service;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.entity.QrCode;
import com.example.closed_club.repository.ParticipantRepository;
import com.example.closed_club.repository.QrCodeRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class EntryService {
    private final ParticipantRepository participantRepository;
    private final QrCodeRepository qrCodeRepository;

    public EntryService(QrCodeRepository qrCodeRepository, ParticipantRepository participantRepository) {
        this.qrCodeRepository = qrCodeRepository;
        this.participantRepository = participantRepository;
    }

    public Participant checkEntry(UUID uuid){
        Optional<QrCode> qr = qrCodeRepository.findByUuid(uuid);
        if (qr.isEmpty()) {
            throw new RuntimeException("Вход запрещён: код не найден");
        } else {
             QrCode qrCode = qr.get();
             Participant participant = qrCode.getParticipant();
             UUID newUuid = UUID.randomUUID();
             qrCode.setUuid(newUuid);
             qrCodeRepository.save(qrCode);
             return participant;
        }
    }
}
