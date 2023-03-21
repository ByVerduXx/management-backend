package com.ofc.management.controller;

import com.ofc.management.model.dto.RehersalRequestDTO;
import com.ofc.management.model.dto.RehersalResponseDTO;
import com.ofc.management.service.RehersalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rehersals")
@RequiredArgsConstructor
public class RehersalController {

    private final RehersalService rehersalService;

    @GetMapping
    public ResponseEntity<List<RehersalResponseDTO>> findAll() {
        return ResponseEntity.ok(rehersalService.findAll());
    }

    @PostMapping
    public ResponseEntity<RehersalResponseDTO> createRehersal(@RequestBody RehersalRequestDTO rehersalRequestDTO) {
        return ResponseEntity.ok(rehersalService.createRehersal(rehersalRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RehersalResponseDTO> getRehersal(@PathVariable Integer id) {
        return ResponseEntity.ok(rehersalService.findById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<RehersalResponseDTO> updateRehersal(@RequestBody RehersalRequestDTO rehersalRequestDTO, @PathVariable Integer id) {
        return ResponseEntity.ok(rehersalService.updateRehersal(rehersalRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRehersal(@PathVariable Integer id) {
        rehersalService.deleteRehersal(id);
        return ResponseEntity.ok("Ensayo eliminado correctamente");
    }
}
