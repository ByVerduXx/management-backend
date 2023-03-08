package com.ofc.management.service;

import com.ofc.management.model.User;
import com.ofc.management.model.dto.MusicianRequestDTO;
import com.ofc.management.model.dto.MusicianResponseDTO;
import com.ofc.management.model.mapper.UserMapper;
import com.ofc.management.repository.UserRepository;
import com.ofc.management.service.exception.UsernameAlreadyExists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public MusicianResponseDTO createMusician(MusicianRequestDTO musicianRequestDTO) {

        if (userRepository.findFirstByUsername(musicianRequestDTO.getUsername()).isPresent()) {
            throw new UsernameAlreadyExists();
        }
        User musician = userMapper.toUser(musicianRequestDTO);
        userRepository.save(musician);
        return userMapper.toMusicianResponseDTO(musician);
    }

    public List<MusicianResponseDTO> findAllMusicians() {
        Iterator<User> usersIterator = userRepository.findAll().iterator();
        List<MusicianResponseDTO> musicians = new ArrayList<>();
        while (usersIterator.hasNext()) {
            User user = usersIterator.next();
            if (user.getPosition() == null) {
                musicians.add(userMapper.toMusicianResponseDTO(user));
            }
        }
        return musicians;
    }

    public MusicianResponseDTO updateMusician() {
        return new MusicianResponseDTO();
    }

}
