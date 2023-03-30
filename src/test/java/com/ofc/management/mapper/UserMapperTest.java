package com.ofc.management.mapper;

import com.ofc.management.model.Instrument;
import com.ofc.management.model.User;
import com.ofc.management.model.dto.InstrumentRequestDTO;
import com.ofc.management.model.dto.InstrumentResponseDTO;
import com.ofc.management.model.dto.UserRequestDTO;
import com.ofc.management.model.dto.UserResponseDTO;
import com.ofc.management.model.mapper.InstrumentMapper;
import com.ofc.management.model.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class UserMapperTest {

    private final InstrumentMapper instrumentMapper = mock(InstrumentMapper.class);
    private final UserMapper userMapper = new UserMapper(instrumentMapper);

    private final User DUMMY_USER = new User(1, "name", "lastName", "username", "password", null, "role");
    private final UserRequestDTO DUMMY_USER_REQUEST_DTO = new UserRequestDTO("name", "lastName", "username", "password", null, "role");
    private final UserResponseDTO DUMMY_USER_RESPONSE_DTO = new UserResponseDTO(1, "name", "lastName", "username", null, "role");
    private final Instrument DUMMY_INSTRUMENT = new Instrument(1, "Instrument");
    private final InstrumentRequestDTO DUMMY_INSTRUMENT_REQUEST_DTO = new InstrumentRequestDTO("Instrument");
    private final InstrumentResponseDTO DUMMY_INSTRUMENT_RESPONSE_DTO = new InstrumentResponseDTO(1, "Instrument");

    @Test
    @DisplayName("Test toUser method for a user with no instrument")
    void testToUserWithoutInstrument() {

        User user = userMapper.toUser(DUMMY_USER_REQUEST_DTO);

        assertEquals(DUMMY_USER, user);
        verifyNoInteractions(instrumentMapper);
    }

    @Test
    @DisplayName("Test toUser method for a user with an instrument")
    void testToUserWithInstrument() {

        DUMMY_USER_REQUEST_DTO.setInstrument(DUMMY_INSTRUMENT_REQUEST_DTO);
        DUMMY_USER.setInstrument(DUMMY_INSTRUMENT);

        when(instrumentMapper.toInstrument(DUMMY_INSTRUMENT_REQUEST_DTO)).thenReturn(DUMMY_INSTRUMENT);
        User user = userMapper.toUser(DUMMY_USER_REQUEST_DTO);

        assertEquals(DUMMY_USER, user);
        verify(instrumentMapper).toInstrument(DUMMY_INSTRUMENT_REQUEST_DTO);
    }

    @Test
    @DisplayName("Test toUserResponseDTO method for a user with no instrument")
    void testToUserResponseDTO() {

        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(DUMMY_USER);

        assertEquals(DUMMY_USER_RESPONSE_DTO, userResponseDTO);
        verifyNoInteractions(instrumentMapper);
    }

    @Test
    @DisplayName("Test toUserResponseDTO method for a user with an instrument")
    void testToUserResponseDTOWithInstrument() {

        DUMMY_USER.setInstrument(DUMMY_INSTRUMENT);
        DUMMY_USER_RESPONSE_DTO.setInstrument(DUMMY_INSTRUMENT_RESPONSE_DTO);

        when(instrumentMapper.toInstrumentResponseDTO(DUMMY_INSTRUMENT)).thenReturn(DUMMY_INSTRUMENT_RESPONSE_DTO);
        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(DUMMY_USER);

        assertEquals(DUMMY_USER_RESPONSE_DTO, userResponseDTO);
        verify(instrumentMapper).toInstrumentResponseDTO(DUMMY_INSTRUMENT);
    }

}
