package com.ofc.management.service;

import com.ofc.management.model.Concert;
import com.ofc.management.model.dto.ConcertRequestDTO;
import com.ofc.management.model.dto.ConcertResponseDTO;
import com.ofc.management.model.mapper.ConcertMapper;
import com.ofc.management.repository.ConcertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertRepository concertRepository;

    private final ConcertMapper concertMapper;

    public ConcertResponseDTO createConcert(ConcertRequestDTO concertRequestDTO) {
        Concert concert = concertMapper.toConcert(concertRequestDTO);
        concertRepository.save(concert);
        return concertMapper.toConcertResponseDTO(concert);
    }
    
}
