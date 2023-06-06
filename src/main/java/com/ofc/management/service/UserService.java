package com.ofc.management.service;

import com.ofc.management.model.Concert;
import com.ofc.management.model.MusicianConcert;
import com.ofc.management.model.User;
import com.ofc.management.model.dto.*;
import com.ofc.management.model.mapper.CalendarEventMapper;
import com.ofc.management.model.mapper.UserMapper;
import com.ofc.management.repository.*;
import com.ofc.management.security.JWTService;
import com.ofc.management.service.exception.*;
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

    private final ConcertRepository concertRepository;

    private final RehersalRepository rehersalRepository;
    private final InstrumentRepository instrumentRepository;

    private final MusicianConcertRepository musicianConcertRepository;
    private final UserMapper userMapper;

    private final CalendarEventMapper calendarEventMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JWTService jwtService;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        if (userRepository.findFirstByUsername(userRequestDTO.getUsername()).isPresent()) {
            throw new UsernameAlreadyExists();
        }
        User user = userMapper.toUser(userRequestDTO);
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));
        user.setInstrument(instrumentRepository.findFirstByName(userRequestDTO.getInstrument().getName()).orElseThrow(InstrumentDoesNotExist::new));
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
        User user = userRepository.findUserById(id).orElseThrow(UserDoesNotExist::new);
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
            user.setInstrument(instrumentRepository.findFirstByName(userUpdateDTO.getInstrument().getName()).orElseThrow(InstrumentDoesNotExist::new));
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

    public UserResponseDTO findUserById(Integer id) {
        User user = userRepository.findUserById(id).orElseThrow(UserDoesNotExist::new);
        return userMapper.toUserResponseDTO(user);
    }

    public void deleteUser(Integer id, String jwt) {
        User user = userRepository.findUserById(id).orElseThrow(UserDoesNotExist::new);
        if (user.getUsername().equals(jwtService.extractUsername(jwt))) {
            throw new AdminCannotDeleteHimself();
        }
        userRepository.delete(user);
    }

    public void updatePassword(String token, ChangePasswordDTO changePasswordDTO) {
        User user = userRepository.findFirstByUsername(jwtService.extractUsername(token)).orElseThrow(UserDoesNotExist::new);
        if (!bCryptPasswordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new OldPasswordDoesNotMatch();
        }
        if (changePasswordDTO.getNewPassword().isBlank()) {
            throw new VoidPasswordNotValid();
        }
        user.setPassword(bCryptPasswordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    public void resetUserPassword(Integer id, ResetPasswordDTO password) {
        User user = userRepository.findUserById(id).orElseThrow(UserDoesNotExist::new);
        user.setPassword(bCryptPasswordEncoder.encode(password.getPassword()));
        userRepository.save(user);
    }

    public List<CalendarEventDTO> getCalendarEvents(String token) {
        User user = userRepository.findFirstByUsername(jwtService.extractUsername(token)).orElseThrow(UserDoesNotExist::new);
        List<CalendarEventDTO> calendarEventDTOS = new ArrayList<>();
        if (user.getRole().equals("ADMIN")) {
            calendarEventDTOS.addAll(calendarEventMapper.fromConcertToCalendarEventDTOs(concertRepository.findAll()));
            calendarEventDTOS.addAll(calendarEventMapper.fromRehersalToCalendarEventDTOs(rehersalRepository.findAll()));
            return calendarEventDTOS;
        }
        List<Concert> userConcerts = musicianConcertRepository.findAllByUser(user).stream().filter(MusicianConcert::isAccepted).map(MusicianConcert::getConcert).toList();
        calendarEventDTOS.addAll(calendarEventMapper.fromConcertToCalendarEventDTOs(userConcerts));
        userConcerts.forEach(concert -> calendarEventDTOS.addAll(calendarEventMapper.fromRehersalToCalendarEventDTOs(concert.getRehersals())));
        return calendarEventDTOS;
    }

    public UserResponseDTO getProfile(String jwt) {
        User user = userRepository.findFirstByUsername(jwtService.extractUsername(jwt)).orElseThrow(UserDoesNotExist::new);
        return userMapper.toUserResponseDTO(user);
    }
}
