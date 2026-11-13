package com.example.closed_club.service;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.repository.ParticipantRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    public Participant findById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found"));
    }

    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }

    public void deleteById(Long id) {
        participantRepository.deleteById(id);
    }

    public Participant update (Long id, Participant upd) {
        Participant p = participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found"));
        p.setName(upd.getName());
        p.setLastName(upd.getLastName());
        p.setPostName(upd.getPostName());
        return participantRepository.save(p);
    }


}
