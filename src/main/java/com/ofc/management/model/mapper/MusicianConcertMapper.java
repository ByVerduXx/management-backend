package com.ofc.management.model.mapper;

import com.ofc.management.model.MusicianConcert;
import com.ofc.management.model.dto.MusicianConcertResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MusicianConcertMapper {

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
