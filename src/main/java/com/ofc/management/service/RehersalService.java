package com.ofc.management.service;

import com.ofc.management.model.Rehersal;
import com.ofc.management.model.dto.RehersalRequestDTO;
import com.ofc.management.model.dto.RehersalResponseDTO;
import com.ofc.management.model.mapper.RehersalMapper;
import com.ofc.management.repository.ConcertRepository;
import com.ofc.management.repository.RehersalRepository;
import com.ofc.management.service.exception.ConcertDoesNotExist;
import com.ofc.management.service.exception.NoDateProvided;
import com.ofc.management.service.exception.RehersalDoesNotExist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RehersalService {

    private final RehersalRepository rehersalRepository;
    private final RehersalMapper rehersalMapper;
    private final ConcertRepository concertRepository;

    public List<RehersalResponseDTO> findAll() {
        return rehersalMapper.toRehersalResponseDTOs(rehersalRepository.findAll());
    }

    public RehersalResponseDTO findById(Integer id) {
        return rehersalMapper.toRehersalResponseDTO(rehersalRepository.findById(id).orElseThrow(RehersalDoesNotExist::new));
    }

    public RehersalResponseDTO createRehersal(RehersalRequestDTO rehersalRequestDTO) {
        if (rehersalRequestDTO.getDate() == null) {
            throw new NoDateProvided();
        }
        Rehersal rehersal = rehersalMapper.toRehersal(rehersalRequestDTO);
        rehersal.setConcert(concertRepository.findById(rehersalRequestDTO.getConcert()).orElseThrow(ConcertDoesNotExist::new));

        return rehersalMapper.toRehersalResponseDTO(rehersalRepository.save(rehersal));
    }

    public RehersalResponseDTO updateRehersal(RehersalRequestDTO rehersalRequestDTO, Integer id) {
        Rehersal rehersal = rehersalRepository.findById(id).orElseThrow(RehersalDoesNotExist::new);

        if (rehersalRequestDTO.getDate() == null) {
            throw new NoDateProvided();
        }

        rehersal.setDate(rehersalRequestDTO.getDate());
        rehersal.setPlace(rehersalRequestDTO.getPlace());

        return rehersalMapper.toRehersalResponseDTO(rehersalRepository.save(rehersal));
    }

    public void deleteRehersal(Integer id) {
        Rehersal rehersal = rehersalRepository.findById(id).orElseThrow(RehersalDoesNotExist::new);
        rehersalRepository.delete(rehersal);
    }
}
