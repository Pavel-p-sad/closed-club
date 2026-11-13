//Пока что вместо обработанной ошибки прилетает простыня. Исправлю позже
package com.example.closed_club.controller;
import com.example.closed_club.dto.ParticipantDto;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.service.ParticipantService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantRESTController {
    private final ParticipantService  participantService;
    public ParticipantRESTController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public List<ParticipantDto> findAll() {
        List<Participant> p = participantService.findAll();
        List<ParticipantDto> dto = new ArrayList<>();
        for (Participant p1 : p) {
            dto.add(new ParticipantDto(p1.getId(), p1.getName(), p1.getLastName(), p1.getPostName()));
        }
        return dto;
    }

    @GetMapping("/{id}")
    public ParticipantDto findById(@PathVariable Long id) {
        Participant p = participantService.findById(id);
        return new ParticipantDto(p.getId(), p.getName(), p.getLastName(), p.getPostName());
    }

    @PostMapping
    public ParticipantDto create(@RequestBody Participant participant) {
        Participant saved = participantService.save(participant);
        return new ParticipantDto(saved.getId(), saved.getName(), saved.getLastName(), saved.getPostName());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        participantService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ParticipantDto update(@RequestBody Participant participant, @PathVariable Long id) {
        Participant up = participantService.update( id, participant);
        return new ParticipantDto( up.getId(), up.getName(), up.getLastName(), up.getPostName());
    }

}
