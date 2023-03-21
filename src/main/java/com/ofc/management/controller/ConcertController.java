package com.ofc.management.controller;

import com.ofc.management.model.dto.ConcertRequestDTO;
import com.ofc.management.model.dto.ConcertResponseDTO;
import com.ofc.management.model.dto.MusicianConcertRequestDTO;
import com.ofc.management.model.dto.MusicianConcertResponseDTO;
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

}
