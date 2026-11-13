package com.example.closed_club.controller;
import com.example.closed_club.dto.QrCodeDto;
import com.example.closed_club.entity.QrCode;
import com.example.closed_club.service.QrCodeService;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/qr-codes")
public class QrCodeRESTController {
    private final QrCodeService qrCodeService;

    public QrCodeRESTController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping
    public List<QrCodeDto> findAll() {
        List<QrCode> qr = qrCodeService.findAll();
        List<QrCodeDto> qrDto =  new ArrayList<>();
        for (QrCode qrCode : qr) {
             qrDto.add(new QrCodeDto( qrCode.getId(), qrCode.getUuid(), qrCode.getDateTime(), qrCode.getParticipant().getId()));
         }
        return qrDto;
    }
    @GetMapping("/{id}")
    public QrCodeDto findById(@PathVariable Long id) {
        QrCode q = qrCodeService.findById(id);
        return new QrCodeDto( q.getId(), q.getUuid(), q.getDateTime(), q.getParticipant().getId());
    }
    @PostMapping
    public QrCodeDto create(@RequestParam Long participantId) {
        QrCode q = qrCodeService.create(participantId);
        return new QrCodeDto(q.getId(), q.getUuid(), q.getDateTime(), q.getParticipant().getId());
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        qrCodeService.delete(id);
    }
}
