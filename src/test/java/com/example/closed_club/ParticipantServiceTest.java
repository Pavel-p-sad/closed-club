package com.example.closed_club;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.repository.ParticipantRepository;
import com.example.closed_club.service.ParticipantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParticipantServiceTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void FindById_shouldReturnParticipant_whenUuidExists() {
        Participant p = new Participant();
        p.setName("Martin");
        p.setLastName("Luter");
        p.setPostName("Qrecer");
        p.setId(392L);

        when(participantRepository.findById(392L)).thenReturn(Optional.of(p));
        Participant result = participantService.findById(p.getId());
        assertEquals(392L, result.getId());
        assertEquals("Martin", result.getName());
        assertEquals("Luter", result.getLastName());
        assertEquals("Qrecer", result.getPostName());
    }
}
