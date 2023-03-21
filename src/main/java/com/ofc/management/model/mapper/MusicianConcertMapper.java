package com.ofc.management.model.mapper;

import com.ofc.management.model.MusicianConcert;
import com.ofc.management.model.MusicianConcertPK;
import com.ofc.management.model.dto.MusicianConcertRequestDTO;
import com.ofc.management.model.dto.MusicianConcertResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MusicianConcertMapper {

    public MusicianConcert toMusicianConcert(MusicianConcertRequestDTO musicianConcertRequestDTO) {
        MusicianConcert musicianConcert = new MusicianConcert();
        MusicianConcertPK musicianConcertPK = new MusicianConcertPK(musicianConcertRequestDTO.getUserId(), musicianConcertRequestDTO.getConcertId());

        musicianConcert.setId(musicianConcertPK);
        musicianConcert.setPayment(musicianConcertRequestDTO.getPayment());
        musicianConcert.setRole(musicianConcertRequestDTO.getRole());
        musicianConcert.setAccepted(musicianConcertRequestDTO.isAccepted());
        musicianConcert.setPending(musicianConcertRequestDTO.isPending());

        return musicianConcert;
    }

    public List<MusicianConcert> toMusicianConcerts(List<MusicianConcertRequestDTO> musicianConcertRequestDTOs) {
        return musicianConcertRequestDTOs.stream().map(this::toMusicianConcert).toList();
    }

    public MusicianConcertResponseDTO toMusicianConcertResponseDTO(MusicianConcert musicianConcert) {
        MusicianConcertResponseDTO musicianConcertResponseDTO = new MusicianConcertResponseDTO();

        musicianConcertResponseDTO.setUsername(musicianConcert.getUser().getUsername());
        musicianConcertResponseDTO.setRole(musicianConcert.getRole());
        musicianConcertResponseDTO.setPayment(musicianConcert.getPayment());
        musicianConcertResponseDTO.setAccepted(musicianConcert.isAccepted());
        musicianConcertResponseDTO.setPending(musicianConcert.isPending());

        return musicianConcertResponseDTO;
    }

    public List<MusicianConcertResponseDTO> toMusicianConcertResponseDTOs(List<MusicianConcert> musicianConcerts) {
        return musicianConcerts.stream().map(this::toMusicianConcertResponseDTO).toList();
    }
}
