package com.ofc.management.controller;

import com.ofc.management.model.dto.InstrumentResponseDTO;
import com.ofc.management.service.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/instruments")
@RequiredArgsConstructor
public class InstrumentController {

    private final InstrumentService instrumentService;

    @GetMapping
    public ResponseEntity<List<InstrumentResponseDTO>> findAll() {
        return ResponseEntity.ok(instrumentService.findAll());
    }
}
