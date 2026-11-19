package com.example.closed_club.controller;
import com.example.closed_club.dto.QrCodeDto;
import com.example.closed_club.service.QrCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<QrCodeDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(qrCodeService.findAllDtosQr(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<QrCodeDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(qrCodeService.findByIdDto(id));
    }
    @PostMapping
    public ResponseEntity<QrCodeDto> create(@RequestParam Long participantId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(qrCodeService.createDto(participantId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        qrCodeService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/participant/{participantId}")
    public ResponseEntity<Page<QrCodeDto>> findByParticipantId(@PathVariable Long participantId,  Pageable pageable) {
        return ResponseEntity.ok(qrCodeService.findByParticipantId(participantId, pageable));
    }
}
