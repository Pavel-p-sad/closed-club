package com.example.closed_club.controller;
import com.example.closed_club.dto.ParticipantDto;
import com.example.closed_club.service.ParticipantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/participants")
@RequiredArgsConstructor
public class ParticipantRESTController {
    private final ParticipantService participantService;

    @GetMapping
    public ResponseEntity<Page<ParticipantDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(participantService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(participantService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ParticipantDto> create(@Valid @RequestBody ParticipantDto participant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(participantService.create(participant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        participantService.deleteById(id);
        return ResponseEntity.ok("Participant deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDto> update(@Valid @RequestBody ParticipantDto participant, @PathVariable Long id) {
        return ResponseEntity.ok(participantService.update(id, participant));
    }

}