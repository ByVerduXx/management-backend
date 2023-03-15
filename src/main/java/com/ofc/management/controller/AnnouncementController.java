package com.ofc.management.controller;

import com.ofc.management.model.dto.AnnouncementRequestDTO;
import com.ofc.management.model.dto.AnnouncementResponseDTO;
import com.ofc.management.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_CONDUCTOR"})
    public ResponseEntity<AnnouncementResponseDTO> createAnnouncement(@RequestBody AnnouncementRequestDTO announcementRequestDTO, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(announcementService.createAnnouncement(announcementRequestDTO, token.substring(7)), HttpStatus.CREATED);
    }
}
