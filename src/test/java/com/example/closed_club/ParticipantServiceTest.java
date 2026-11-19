package com.example.closed_club;
import com.example.closed_club.dto.ParticipantDto;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.exception.NotFoundException;
import com.example.closed_club.repository.ParticipantRepository;
import com.example.closed_club.service.ParticipantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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
        ParticipantDto result = participantService.findById(p.getId());
        assertEquals(392L, result.id());
        assertEquals("Martin", result.name());
        assertEquals("Luter", result.lastName());
        assertEquals("Qrecer", result.postName());
    }
    @Test
    void findByIdShouldThrowNotFoundExceptionWhenNotExists() {
        when(participantRepository.findById(456L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> participantService.findById(456L));
    }

    @Test
    void shouldSaveAndReturnParticipantDtoWhenCallCreate(){
        ParticipantDto pD = new ParticipantDto(null, "Martin", "Luter", "Qrecer");
        Participant savedEntity = new Participant();
        savedEntity.setId(392L);
        savedEntity.setName("Martin");
        savedEntity.setLastName("Luter");
        savedEntity.setPostName("Qrecer");

        when(participantRepository.save(any(Participant.class))).thenReturn(savedEntity);
        ParticipantDto result = participantService.create(pD);
        assertNotNull(result.id());
        assertEquals("Martin", result.name());
        assertEquals("Luter", result.lastName());
        assertEquals("Qrecer", result.postName());
        assertEquals(392L, result.id());
        verify(participantRepository, times(1)).save(any(Participant.class));


    }



}
