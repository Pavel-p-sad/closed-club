package com.example.closed_club.controller;
import com.example.closed_club.dto.QrCodeDto;
import com.example.closed_club.entity.QrCode;
import com.example.closed_club.service.QrCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/qr-codes")
@RequiredArgsConstructor
public class QrCodeRESTController {
    private final QrCodeService qrCodeService;

    @GetMapping
    public ResponseEntity<List<QrCodeDto>> findAll() {
        return ResponseEntity.ok(qrCodeService.findAllDtosQr());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QrCodeDto> findById(@PathVariable Long id) {
        QrCode q = qrCodeService.findById(id);
        QrCodeDto dto = new QrCodeDto(q.getId(), q.getUuid(), q.getDateTime(), q.getParticipant().getId());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<QrCodeDto> create(@RequestParam Long participantId) {
        QrCode q = qrCodeService.create(participantId);
        QrCodeDto dto = new QrCodeDto(q.getId(), q.getUuid(), q.getDateTime(), q.getParticipant().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        qrCodeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
