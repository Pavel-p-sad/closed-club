package com.example.closed_club.service;
import com.example.closed_club.dto.ParticipantDto;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.exception.NotFoundException;
import com.example.closed_club.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public List<Participant> findAll() {
        return participantRepository.findAll();
    }
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
    public List<ParticipantDto> findAllDto() {
        List<Participant> p = findAll();
        List<ParticipantDto> dto = new ArrayList<>();
        for (Participant p1 : p) {
            dto.add(new ParticipantDto(p1.getId(), p1.getName(), p1.getLastName(), p1.getPostName()));
        }
        return dto;
    }
    public ParticipantDto findByIdDto(Long id) {
        Participant p = findById(id);
        return new ParticipantDto(p.getId(), p.getName(), p.getLastName(), p.getPostName());
    }
    public ParticipantDto createDto(Participant participant) {
        Participant saved = save(participant);
        return new ParticipantDto(saved.getId(), saved.getName(), saved.getLastName(), saved.getPostName());
    }
    public ParticipantDto updateDto(Participant participant, Long id) {
        Participant up = update( id, participant);
        return new ParticipantDto( up.getId(), up.getName(), up.getLastName(), up.getPostName());
    }
}
