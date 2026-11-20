package com.example.closed_club.service;
import com.example.closed_club.dto.ParticipantDto;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.exception.NotFoundException;
import com.example.closed_club.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }
    public void deleteById(Long id) {
        participantRepository.deleteById(id);
    }
    public ParticipantDto update (Long id, ParticipantDto upd) {
        Participant p = participantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Participant not found"));
        p.setName(upd.name());
        p.setLastName(upd.lastName());
        p.setPostName(upd.postName());
        Participant saved = save(p);
        return new ParticipantDto(saved.getId(), saved.getName(), saved.getLastName(), saved.getPostName());
    }
    public Page<ParticipantDto> findAll(Pageable pageable) {
        return participantRepository.findAll(pageable).map(p -> new ParticipantDto(p.getId(), p.getName(), p.getLastName(), p.getPostName()));
    }
    public ParticipantDto findById(Long id) {
        return participantRepository.findById(id).map(p -> new ParticipantDto(p.getId(), p.getName(), p.getLastName(), p.getPostName()))
                .orElseThrow(() -> new NotFoundException("не найдено"));
    }
    public ParticipantDto create(ParticipantDto par) {
        Participant p = new Participant(par.name(), par.lastName(), par.postName());
        Participant saved = save(p);
        return new ParticipantDto(saved.getId(), saved.getName(), saved.getLastName(), saved.getPostName());
    }
    public void createParticipant(String firstName, String lastName, String postName) {
        Participant p = new Participant(firstName, lastName,  postName);
        participantRepository.save(p);
    }
}