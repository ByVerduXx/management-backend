package com.ofc.management.controller;

import com.ofc.management.model.dto.*;
import com.ofc.management.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concerts")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;

    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<ConcertResponseDTO>> findAll() {
        return ResponseEntity.ok(concertService.findAll());
    }


    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<ConcertResponseDTO> createConcert(@RequestBody ConcertRequestDTO concertRequestDTO) {
        return ResponseEntity.ok(concertService.createConcert(concertRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConcertResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(concertService.findById(id));
    }

    @PostMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<ConcertResponseDTO> updateConcert(@PathVariable Integer id, @RequestBody ConcertRequestDTO concertRequestDTO) {
        return ResponseEntity.ok(concertService.updateConcert(id, concertRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<String> deleteConcert(@PathVariable Integer id) {
        concertService.deleteConcert(id);
        return ResponseEntity.ok("Concierto eliminado correctamente");
    }

    @PostMapping("{id}/accept")
    public ResponseEntity<List<ConcertProfileDTO>> acceptConcert(@PathVariable Integer id, @RequestHeader("Authorization") String token, @RequestBody AcceptConcertDTO accepted) {
        String jwt = token.substring(7);
        concertService.acceptConcert(id, jwt, accepted.getAccepted());
        return ResponseEntity.ok(concertService.findUserInvites(jwt));
    }

    @PostMapping("{id}/musicians")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<MusicianConcertResponseDTO>> addMusicians(@PathVariable Integer id, @RequestBody List<MusicianConcertRequestDTO> musicianConcertRequestDTOs) {
        return ResponseEntity.ok(concertService.addMusicians(id, musicianConcertRequestDTOs));
    }

    @DeleteMapping("{id}/musicians/{musicianId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<String> deleteMusician(@PathVariable Integer id, @PathVariable Integer musicianId) {
        concertService.deleteMusician(id, musicianId);
        return ResponseEntity.ok("Músico eliminado correctamente");
    }

    @GetMapping("/profile")
    public ResponseEntity<List<ConcertProfileDTO>> findUserConcerts(@RequestHeader("Authorization") String token, @RequestParam(name = "accepted", required = false, defaultValue = "true") boolean accepted) {
        return accepted ? ResponseEntity.ok(concertService.findUserConcerts(token.substring(7))) : ResponseEntity.ok(concertService.findUserInvites(token.substring(7)));
    }

}
