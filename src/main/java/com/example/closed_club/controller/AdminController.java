// Этот контроллер можно не брать во внимание, я его делал не по заданию а для себя , чтобы был интерфейс для простого управления БД
package com.example.closed_club.controller;
import com.example.closed_club.entity.Participant;
import com.example.closed_club.entity.QrCode;
import com.example.closed_club.repository.ParticipantRepository;
import com.example.closed_club.repository.QrCodeRepository;
import com.example.closed_club.service.EntryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/api/admin")
public class AdminController {
    private final QrCodeRepository qrCodeRepository;
    private final ParticipantRepository participantRepository;
    private final EntryService entryService;

    public AdminController(QrCodeRepository qrCodeRepository, ParticipantRepository participantRepository, EntryService entryService) {
         this.qrCodeRepository = qrCodeRepository;
         this.participantRepository = participantRepository;
         this.entryService = entryService;
    }

    @GetMapping("/page")
    public String adminPage(){
        return "admin";
    }

    @PostMapping("/participants")
    public String addParticipant(
            @RequestParam String name,
            @RequestParam String lastName,
            @RequestParam String postName
        ) {
        Participant p = new Participant();
        p.setName(name);
        p.setLastName(lastName);
        p.setPostName(postName);
        participantRepository.save(p);
        return "redirect:/api/admin/page";
    }

    @PostMapping("/participants/delete")
    public String deleteParticipant(@RequestParam Long id)  {
        participantRepository.deleteById(id);
        return "redirect:/api/admin/page";
    }

    @PostMapping("/clear")
    public String clearDatabase() {
        qrCodeRepository.deleteAll();
        participantRepository.deleteAll();
        return "redirect:/api/admin/page";
    }

    @PostMapping("/participants/give-qr")
    public String giveQrCode(@RequestParam Long id){
        Participant p = participantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found"));
        QrCode qr = new QrCode();
        qr.setUuid(UUID.randomUUID());
        qr.setDateTime(LocalDateTime.now());
        qr.setParticipant(p);
        qrCodeRepository.save(qr);
        return "redirect:/api/admin/page";
    }

    @GetMapping("/participants/list")
    public String listParticipants(org.springframework.ui.Model model){
        model.addAttribute("participants", participantRepository.findAll());
        return "participants";
    }

    @PostMapping("/check-entry")
    public String checkEntry(@RequestParam String uuid, org.springframework.ui.Model model) {
        try{
            Participant p = entryService.checkEntry(UUID.fromString(uuid));
            model.addAttribute("result", "Проход разрешен," + p.getName() + " " + p.getLastName());
            model.addAttribute("success", true);
        } catch (Exception e ) {
            model.addAttribute("result", "ДОСТУП ЗАПРЕЩЕН: " + e.getMessage());
            model.addAttribute("success", false);
        }
        return "admin";
    }

    @PostMapping("/qr-code/clear")
    public String clearQrCode(@RequestParam Long id){
        if (qrCodeRepository.existsById(id)) {
            qrCodeRepository.deleteById(id);
            return "redirect:/api/admin/page";
        } else {
            return "notFound";
        }
    }


}

