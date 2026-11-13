package com.example.closed_club.controller;
import com.example.closed_club.dto.ParticipantDto;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.service.EntryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/api/entry")
public class EntryController {
    public final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping
    public ParticipantDto checkEntry(@RequestParam UUID uuid) {

        Participant p = entryService.checkEntry(uuid);
        return new ParticipantDto(p.getId(), p.getName(), p.getLastName(), p.getPostName());
    }
}