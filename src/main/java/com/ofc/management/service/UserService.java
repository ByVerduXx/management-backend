package com.ofc.management.service;

import com.ofc.management.model.User;
import com.ofc.management.model.dto.UserRequestDTO;
import com.ofc.management.model.dto.UserResponseDTO;
import com.ofc.management.model.mapper.UserMapper;
import com.ofc.management.repository.InstrumentRepository;
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
    private final InstrumentRepository instrumentRepository;
    private final UserMapper userMapper;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        if (userRepository.findFirstByUsername(userRequestDTO.getUsername()).isPresent()) {
            throw new UsernameAlreadyExists();
        }
        User user = userMapper.toUser(userRequestDTO);
        user.setInstrument(instrumentRepository.findFirstByName(userRequestDTO.getInstrument().getName()).orElseThrow());
        userRepository.save(user);
        return userMapper.toUserResponseDTO(user);
    }

    public List<UserResponseDTO> findAllMusicians() {
        Iterator<User> usersIterator = userRepository.findAll().iterator();
        List<UserResponseDTO> musicians = new ArrayList<>();
        while (usersIterator.hasNext()) {
            User user = usersIterator.next();
            if (user.getRole().equals("USER")) {
                musicians.add(userMapper.toUserResponseDTO(user));
            }
        }
        return musicians;
    }

    public UserResponseDTO updateUser(Integer id, UserRequestDTO userRequestDTO) {
        //TODO throw user does not exist
        User user = userRepository.findUserById(id).orElseThrow();
        user.setName(userRequestDTO.getName());
        user.setLastName(userRequestDTO.getLastName());
        user.setInstrument(userMapper.toUser(userRequestDTO).getInstrument());
        userRepository.save(user);
        return userMapper.toUserResponseDTO(user);
    }

    public List<UserResponseDTO> findAll() {
        Iterator<User> usersIterator = userRepository.findAll().iterator();
        List<UserResponseDTO> users = new ArrayList<>();
        while (usersIterator.hasNext()) {
            User user = usersIterator.next();
            users.add(userMapper.toUserResponseDTO(user));
        }
        return users;
    }
}
