package com.example.closed_club;
import com.example.closed_club.dto.ParticipantDto;
import com.example.closed_club.dto.QrCodeDto;
import com.example.closed_club.exception.NotFoundException;
import com.example.closed_club.repository.ParticipantRepository;
import com.example.closed_club.repository.QrCodeRepository;
import com.example.closed_club.service.EntryService;
import com.example.closed_club.service.ParticipantService;
import com.example.closed_club.service.QrCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ParticipantServiceIntegrationTest {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private EntryService entryService;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private QrCodeRepository qrCodeRepository;

    @BeforeEach
    void cleanDb() {
        qrCodeRepository.deleteAll();
        participantRepository.deleteAll();
    }

    @Test
    void createAndFindParticipant() {
        ParticipantDto request = new ParticipantDto(null, "Wan", "Wanyk", "qqq");
        ParticipantDto created = participantService.create(request);
        assertNotNull(created.id());

        ParticipantDto found = participantService.findById(created.id());
        assertEquals("Wan", found.name());
        assertEquals("Wanyk", found.lastName());
        assertEquals("qqq", found.postName());
    }

    @Test
    void createQrCodeForParticipant() {

        ParticipantDto part= participantService.create(
                new ParticipantDto(null, "Wan", "Wanyk", "qqq")
        );

        QrCodeDto qrCode = qrCodeService.create(part.id());

        assertNotNull(qrCode.id());
        assertNotNull(qrCode.uuid());
        assertEquals(part.id(), qrCode.participantId());
    }

    @Test
    void qrCodeShouldBeOneTimeUse() {

        ParticipantDto par = participantService.create(
                new ParticipantDto(null, "Wan", "Wanyk", "qqq")
        );
        QrCodeDto qrCode = qrCodeService.create(par.id());
        UUID oldUuid = qrCode.uuid();

        ParticipantDto entryResult = entryService.checkEntry(oldUuid);
        assertEquals("Wan", entryResult.name());

        assertThrows(NotFoundException.class, () -> {
            entryService.checkEntry(oldUuid);
        });
    }
}