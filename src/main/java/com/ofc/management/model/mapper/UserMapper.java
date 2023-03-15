package com.ofc.management.model.mapper;

import com.ofc.management.model.User;
import com.ofc.management.model.dto.UserProfileDTO;
import com.ofc.management.model.dto.UserRequestDTO;
import com.ofc.management.model.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final InstrumentMapper instrumentMapper;

    private final NotificationMapper notificationMapper;

    public User toUser(UserRequestDTO musician) {
        User user = new User();
        user.setName(musician.getName());
        user.setLastName(musician.getLastName());
        user.setUsername(musician.getUsername());
        user.setPassword(musician.getPassword());
        if (musician.getInstrument() != null) {
            user.setInstrument(instrumentMapper.toInstrument(musician.getInstrument()));
        }
        user.setRole(musician.getRole());

        return user;
    }

    public UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setUsername(user.getUsername());
        if (user.getInstrument() != null) {
            userResponseDTO.setInstrument(instrumentMapper.toInstrumentResponseDTO(user.getInstrument()));
        }
        userResponseDTO.setRole(user.getRole());
        return userResponseDTO;
    }

    public UserProfileDTO toUserProfileDTO(User user) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();

        userProfileDTO.setId(user.getId());
        userProfileDTO.setName(user.getName());
        userProfileDTO.setLastName(user.getLastName());
        userProfileDTO.setUsername(user.getUsername());
        if (user.getInstrument() != null) {
            userProfileDTO.setInstrument(instrumentMapper.toInstrumentResponseDTO(user.getInstrument()));
        }
        userProfileDTO.setRole(user.getRole());

        if (!user.getNotifications().isEmpty()) userProfileDTO.setNotifications(notificationMapper.toNotificationsResponseDTO(user.getNotifications()));
        else userProfileDTO.setNotifications(new ArrayList<>());

        return userProfileDTO;
    }

}
