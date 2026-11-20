package com.example.closed_club.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.closed_club.dto.QrCodeDto;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.entity.QrCode;
import com.example.closed_club.exception.NotFoundException;
import com.example.closed_club.repository.ParticipantRepository;
import com.example.closed_club.repository.QrCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QrCodeService {
    private final ParticipantRepository participantRepository;
    private final QrCodeRepository qrCodeRepository;

    public QrCodeDto findById(Long id) {
        return qrCodeRepository.findById(id).map(q -> new QrCodeDto(q.getId(), q.getUuid(), q.getDateTime(), q.getParticipant().getId())).orElseThrow(() -> new NotFoundException("QrCode not found"));
    }
    public QrCodeDto create(Long participantId) {
        Participant p = participantRepository.findById(participantId)
                .orElseThrow(() -> new NotFoundException("Participant not found"));
        QrCode qr = new QrCode(UUID.randomUUID(), LocalDateTime.now(), p);
        QrCode s = qrCodeRepository.save(qr);
        return new QrCodeDto(s.getId(), s.getUuid(), s.getDateTime(), p.getId());
    }
    public boolean delete(Long id) {
        if (qrCodeRepository.existsById(id)) {
            qrCodeRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Page<QrCodeDto> findAllQr(Pageable pageable) {
        return qrCodeRepository.findAll(pageable).map(q -> new QrCodeDto(q.getId(), q.getUuid(), q.getDateTime(), q.getParticipant().getId() ));
    }
    public void giveQrById(Long id) {
        Participant p = participantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Participant not found"));
        QrCode qr = new QrCode(UUID.randomUUID(), LocalDateTime.now(), p);
        qrCodeRepository.save(qr);
    }
    public Page<QrCodeDto> findByParticipantId(Long participantId, Pageable pageable) {
        return qrCodeRepository.findByParticipantId(participantId, pageable)
                .map(q -> new QrCodeDto(q.getId(), q.getUuid(), q.getDateTime(), q.getParticipant().getId()));
    }
}