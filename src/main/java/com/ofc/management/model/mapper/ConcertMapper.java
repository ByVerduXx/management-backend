package com.ofc.management.model.mapper;

import com.ofc.management.model.Concert;
import com.ofc.management.model.dto.ConcertInfoDTO;
import com.ofc.management.model.dto.ConcertRequestDTO;
import com.ofc.management.model.dto.ConcertResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcertMapper {

    private final RehersalMapper rehersalMapper;

    private final MusicianConcertMapper musicianConcertMapper;

    public Concert toConcert(ConcertRequestDTO concertRequestDTO) {
        Concert concert = new Concert();

        concert.setTitle(concertRequestDTO.getTitle());
        concert.setDescription(concertRequestDTO.getDescription());
        concert.setDate(concertRequestDTO.getDate());
        concert.setSoundcheck(concertRequestDTO.getSoundcheck());
        concert.setScores(concertRequestDTO.getScores());

        return concert;
    }

    public ConcertResponseDTO toConcertResponseDTO(Concert concert) {
        ConcertResponseDTO concertResponseDTO = new ConcertResponseDTO();

        concertResponseDTO.setId(concert.getId());
        concertResponseDTO.setTitle(concert.getTitle());
        concertResponseDTO.setDescription(concert.getDescription());
        concertResponseDTO.setDate(concert.getDate());
        concertResponseDTO.setSoundcheck(concert.getSoundcheck());

        return concertResponseDTO;
    }

    public ConcertInfoDTO toConcertInfoDTO(Concert concert) {
        ConcertInfoDTO concertInfoDTO = new ConcertInfoDTO();

        concertInfoDTO.setConcert(toConcertResponseDTO(concert));
        concertInfoDTO.setRehersals(rehersalMapper.toRehersalResponseDTOs(concert.getRehersals()));
        concertInfoDTO.setMusicians(musicianConcertMapper.toMusicianConcertResponseDTOs(concert.getMusicians()));

        return concertInfoDTO;
    }
}
