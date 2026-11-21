package com.example.closed_club.controller;
import com.example.closed_club.dto.ParticipantDto;
import com.example.closed_club.exception.NotFoundException;
import com.example.closed_club.repository.ParticipantRepository;
import com.example.closed_club.repository.QrCodeRepository;
import com.example.closed_club.service.EntryService;
import com.example.closed_club.service.ParticipantService;
import com.example.closed_club.service.QrCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final QrCodeRepository qrCodeRepository;
    private final ParticipantRepository participantRepository;
    private final EntryService entryService;
    private final ParticipantService participantService;
    private final QrCodeService qrCodeService;

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
        participantService.createParticipant(name, lastName, postName);
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
        qrCodeService.giveQrById(id);
        return "redirect:/api/admin/page";
    }

    @GetMapping("/participants/list")
    public String listParticipants(Pageable pageable, org.springframework.ui.Model model){
        model.addAttribute("participants", participantService.findAll(pageable));
        return "participants";
    }

    @PostMapping("/check-entry")
    public String checkEntry(@RequestParam String uuid, org.springframework.ui.Model model) {
        try{
            ParticipantDto p = entryService.checkEntry(UUID.fromString(uuid));
            model.addAttribute("result", "Проход разрешен," +
                    p.name() + " " +
                    p.lastName());
            model.addAttribute("success", true);
        } catch (NotFoundException e ) {
            model.addAttribute("result", "ДОСТУП ЗАПРЕЩЕН: " + e.getMessage());
            model.addAttribute("success", false);
        }
        return "admin";
    }

    @PostMapping("/qr-code/clear")
    public String clearQrCode(@RequestParam Long id){
        if (qrCodeService.delete(id)) {
            return "redirect:/api/admin/page";
        } else {
            return "notFound";
        }
    }

    @GetMapping("/participants/{id}/qr-codes")
    public String listQrCodes(@PathVariable Long id, Pageable pageable, org.springframework.ui.Model model) {
        model.addAttribute("qrCodes", qrCodeService.findByParticipantId(id, pageable));
        model.addAttribute("participantId", id);
        return "qr-codes";
    }
}