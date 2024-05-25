package com.ofc.management.model.mapper;

import com.ofc.management.model.Concert;
import com.ofc.management.model.dto.ConcertProfileDTO;
import com.ofc.management.model.dto.ConcertRequestDTO;
import com.ofc.management.model.dto.ConcertResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConcertMapper {

    private final RehersalMapper rehersalMapper;

    private final MusicianConcertMapper musicianConcertMapper;

    public Concert toConcert(ConcertRequestDTO concertRequestDTO) {
        Concert concert = new Concert();

        concert.setTitle(concertRequestDTO.getTitle());
        concert.setDescription(concertRequestDTO.getDescription());
        concert.setPlace(concertRequestDTO.getPlace());
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
        concertResponseDTO.setPlace(concert.getPlace());
        concertResponseDTO.setDate(concert.getDate());
        concertResponseDTO.setSoundcheck(concert.getSoundcheck());
        concertResponseDTO.setScores(concert.getScores());
        concertResponseDTO.setRehersals(rehersalMapper.toRehersalResponseDTOs(concert.getRehersals()));
        concertResponseDTO.setMusicians(musicianConcertMapper.toMusicianConcertResponseDTOs(concert.getMusicians()));

        return concertResponseDTO;
    }

    public List<ConcertResponseDTO> toConcertResponseDTOs(List<Concert> concerts) {
        return concerts.stream().map(this::toConcertResponseDTO).toList();
    }

    public ConcertProfileDTO toConcertProfileDTO(Concert concert) {
        ConcertProfileDTO concertProfileDTO = new ConcertProfileDTO();

        concertProfileDTO.setId(concert.getId());
        concertProfileDTO.setName(concert.getTitle());
        concertProfileDTO.setDescription(concert.getDescription());
        concertProfileDTO.setPlace(concert.getPlace());
        concertProfileDTO.setDate(concert.getDate());
        concertProfileDTO.setSoundcheck(concert.getSoundcheck());
        concertProfileDTO.setScores(concert.getScores());
        concertProfileDTO.setRehersals(rehersalMapper.toRehersalResponseDTOs(concert.getRehersals()));

        return concertProfileDTO;
    }
}
