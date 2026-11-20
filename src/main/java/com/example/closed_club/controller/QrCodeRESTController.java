package com.example.closed_club.controller;
import com.example.closed_club.dto.QrCodeDto;
import com.example.closed_club.service.QrCodeService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/qr-codes")
@RequiredArgsConstructor
public class QrCodeRESTController {
    private final QrCodeService qrCodeService;

    @GetMapping
    public ResponseEntity<Page<QrCodeDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(qrCodeService.findAllQr(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<QrCodeDto> findById(@Positive @PathVariable Long id) {
        return ResponseEntity.ok(qrCodeService.findById(id));
    }
    @PostMapping
    public ResponseEntity<QrCodeDto> create(@Positive@RequestParam Long participantId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(qrCodeService.create(participantId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@Positive@PathVariable Long id) {
        qrCodeService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
    @GetMapping("/participant/{participantId}")
    public ResponseEntity<Page<QrCodeDto>> findByParticipantId(@Positive@PathVariable Long participantId,  Pageable pageable) {
        return ResponseEntity.ok(qrCodeService.findByParticipantId(participantId, pageable));
    }
}
