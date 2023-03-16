package com.ofc.management.service;

import com.ofc.management.model.dto.InstrumentResponseDTO;
import com.ofc.management.model.mapper.InstrumentMapper;
import com.ofc.management.repository.InstrumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrumentService {

    private final InstrumentRepository instrumentRepository;

    private final InstrumentMapper instrumentMapper;

    public List<InstrumentResponseDTO> findAll() {
        return instrumentMapper.toInstrumentResponseDTOs(instrumentRepository.findAll());
    }
}
