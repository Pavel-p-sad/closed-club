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

    public Participant findById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Participant not found"));
    }
    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }
    public void deleteById(Long id) {
        participantRepository.deleteById(id);
    }
    public Participant update (Long id, Participant upd) {
        Participant p = participantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Participant not found"));
        p.setName(upd.getName());
        p.setLastName(upd.getLastName());
        p.setPostName(upd.getPostName());
        return save(p);
    }
    public Page<ParticipantDto> findAllDto(Pageable pageable) {
        return participantRepository.findAll(pageable).map(p -> new ParticipantDto(p.getId(), p.getName(), p.getLastName(), p.getPostName()));
    }
    public ParticipantDto findByIdDto(Long id) {
        return participantRepository.findById(id).map(p -> new ParticipantDto(p.getId(), p.getName(), p.getLastName(), p.getPostName()))
                .orElseThrow(() -> new NotFoundException("не найдено"));
    }
    public ParticipantDto createDto(Participant participant) {
        Participant saved = save(participant);
        return new ParticipantDto(saved.getId(), saved.getName(), saved.getLastName(), saved.getPostName());
    }
    public ParticipantDto updateDto(Participant participant, Long id) {
        Participant up = update( id, participant);
        return new ParticipantDto( up.getId(), up.getName(), up.getLastName(), up.getPostName());
    }
    public void createParticipant(String firstName, String lastName, String postName) {
        Participant p = new Participant();
        p.setName(firstName);
        p.setLastName(lastName);
        p.setPostName(postName);
        participantRepository.save(p);
    }
}