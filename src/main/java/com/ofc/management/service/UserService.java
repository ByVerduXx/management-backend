package com.ofc.management.service;

import com.ofc.management.model.Instrument;
import com.ofc.management.model.User;
import com.ofc.management.model.dto.UserRequestDTO;
import com.ofc.management.model.dto.UserResponseDTO;
import com.ofc.management.model.dto.UserUpdateDTO;
import com.ofc.management.model.mapper.UserMapper;
import com.ofc.management.repository.InstrumentRepository;
import com.ofc.management.repository.UserRepository;
import com.ofc.management.service.exception.UsernameAlreadyExists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        if (userRepository.findFirstByUsername(userRequestDTO.getUsername()).isPresent()) {
            throw new UsernameAlreadyExists();
        }
        User user = userMapper.toUser(userRequestDTO);
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));
        //TODO throw instrument does not exist
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

    public UserResponseDTO updateUser(Integer id, UserUpdateDTO userUpdateDTO) {
        //TODO throw user does not exist
        User user = userRepository.findUserById(id).orElseThrow();
        user.setName(userUpdateDTO.getName());
        user.setLastName(userUpdateDTO.getLastName());
        if (!user.getUsername().equals(userUpdateDTO.getUsername())) {
            if (!userRepository.findAllByUsername(userUpdateDTO.getUsername()).isEmpty()) {
                throw new UsernameAlreadyExists();
            }
            user.setUsername(userUpdateDTO.getUsername());
        }

        if (userUpdateDTO.getInstrument() == null) {
            user.setInstrument(null);
        } else if (user.getInstrument() == null || !user.getInstrument().getName().equals(userUpdateDTO.getInstrument().getName())){
            user.setInstrument(instrumentRepository.findFirstByName(userUpdateDTO.getInstrument().getName()).orElseThrow());
        }
        user.setRole(userUpdateDTO.getRole());
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
