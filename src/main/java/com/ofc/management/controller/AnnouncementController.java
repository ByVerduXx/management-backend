package com.ofc.management.controller;

import com.ofc.management.model.dto.AnnouncementRequestDTO;
import com.ofc.management.model.dto.AnnouncementResponseDTO;
import com.ofc.management.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;


    @GetMapping
    public ResponseEntity<List<AnnouncementResponseDTO>> getAnnouncements() {
        return ResponseEntity.ok(announcementService.findAll());
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_CONDUCTOR"})
    public ResponseEntity<AnnouncementResponseDTO> createAnnouncement(@RequestBody AnnouncementRequestDTO announcementRequestDTO, @RequestHeader("Authorization") String token) {
        System.out.println(announcementRequestDTO);
        return new ResponseEntity<>(announcementService.createAnnouncement(announcementRequestDTO, token.substring(7)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementResponseDTO> getAnnouncement(@PathVariable Integer id) {
        return ResponseEntity.ok(announcementService.findAnnouncementById(id));
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_CONDUCTOR"})
    public ResponseEntity<String> deleteAnnouncement(@PathVariable Integer id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.ok("Anuncio borrado");
    }

    @PostMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_CONDUCTOR"})
    public ResponseEntity<AnnouncementResponseDTO> updateAnnouncement(@PathVariable Integer id, @RequestBody AnnouncementRequestDTO announcementRequestDTO) {
        return ResponseEntity.ok(announcementService.updateAnnouncement(id, announcementRequestDTO));
    }
}
