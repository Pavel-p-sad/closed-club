package com.example.closed_club.service;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.entity.QrCode;
import com.example.closed_club.repository.ParticipantRepository;
import com.example.closed_club.repository.QrCodeRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class QrCodeService {
    private final ParticipantRepository participantRepository;
    private final QrCodeRepository qrCodeRepository;

    public QrCodeService(ParticipantRepository participantRepository, QrCodeRepository qrCodeRepository) {
        this.participantRepository = participantRepository;
        this.qrCodeRepository = qrCodeRepository;
    }
    public List<QrCode> findAll() {
        return qrCodeRepository.findAll();
    }
    public QrCode findById (Long id) {
        return qrCodeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("QrCode not found"));
    }
    public QrCode create(Long participantId) {
        Participant p = participantRepository.findById(participantId)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found"));
        QrCode qr = new QrCode();
        qr.setUuid(UUID.randomUUID());
        qr.setDateTime(LocalDateTime.now());
        qr.setParticipant(p);
        return qrCodeRepository.save(qr);
    }
    public void delete(Long id) {
        qrCodeRepository.deleteById(id);
    }
}
