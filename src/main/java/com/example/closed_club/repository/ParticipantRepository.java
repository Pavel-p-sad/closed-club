package com.example.closed_club.repository;
import com.example.closed_club.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByLastName(String lastName);
}