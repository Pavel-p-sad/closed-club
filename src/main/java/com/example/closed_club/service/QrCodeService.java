package com.example.closed_club.service;
import com.example.closed_club.dto.QrCodeDto;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.entity.QrCode;
import com.example.closed_club.exception.NotFoundException;
import com.example.closed_club.repository.ParticipantRepository;
import com.example.closed_club.repository.QrCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QrCodeService {
    private final ParticipantRepository participantRepository;
    private final QrCodeRepository qrCodeRepository;

    public List<QrCode> findAll() {
        return qrCodeRepository.findAll();
    }
    public QrCode findById (Long id) {
        return qrCodeRepository.findById(id).orElseThrow(() -> new NotFoundException("QrCode not found"));
    }
    public QrCode create(Long participantId) {
        Participant p = participantRepository.findById(participantId)
                .orElseThrow(() -> new NotFoundException("Participant not found"));
        QrCode qr = new QrCode();
        qr.setUuid(UUID.randomUUID());
        qr.setDateTime(LocalDateTime.now());
        qr.setParticipant(p);
        return qrCodeRepository.save(qr);
    }
    public void delete(Long id) {
        qrCodeRepository.deleteById(id);
    }

    public List<QrCodeDto> findAllDtosQr() {
        List<QrCode> qr = findAll();
        List<QrCodeDto> qrDto = new ArrayList<>();
        for (QrCode qrCode : qr) {
            qrDto.add(new QrCodeDto(qrCode.getId(), qrCode.getUuid(), qrCode.getDateTime(), qrCode.getParticipant().getId()));
        }
        return qrDto;
    }
}
