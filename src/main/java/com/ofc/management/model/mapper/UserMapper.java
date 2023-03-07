package com.ofc.management.model.mapper;

import com.ofc.management.model.User;
import com.ofc.management.model.dto.MusicianRequestDTO;
import com.ofc.management.model.dto.MusicianResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final InstrumentMapper instrumentMapper;

    public User toUser(MusicianRequestDTO musician) {
        User user = new User();
        user.setName(musician.getName());
        user.setLastName(musician.getLastName());
        user.setUsername(musician.getUsername());
        user.setPassword(musician.getPassword());
        user.setInstrument(instrumentMapper.toInstrument(musician.getInstrument()));

        return user;
    }

    public MusicianResponseDTO toMusicianResponseDTO(User user) {
        MusicianResponseDTO musicianResponseDTO = new MusicianResponseDTO();

        musicianResponseDTO.setId(user.getId());
        musicianResponseDTO.setName(user.getName());
        musicianResponseDTO.setLastName(user.getLastName());
        musicianResponseDTO.setUsername(user.getUsername());
        musicianResponseDTO.setInstrument(instrumentMapper.toInstrumentResponseDTO(user.getInstrument()));
        return musicianResponseDTO;
    }

}
