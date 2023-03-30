package com.ofc.management.mapper;

import com.ofc.management.model.*;
import com.ofc.management.model.dto.*;
import com.ofc.management.model.mapper.ConcertMapper;
import com.ofc.management.model.mapper.MusicianConcertMapper;
import com.ofc.management.model.mapper.RehersalMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConcertMapperTest {

    private final RehersalMapper rehersalMapper = mock(RehersalMapper.class);
    private final MusicianConcertMapper musicianConcertMapper = mock(MusicianConcertMapper.class);
    private final ConcertMapper concertMapper = new ConcertMapper(rehersalMapper, musicianConcertMapper);

    private final Concert DUMMY_CONCERT = new Concert(1, "title", "description", LocalDateTime.of(2021, 7, 21, 1, 0), LocalDateTime.of(2021, 7, 21, 1, 0), "scores", Collections.emptyList(), Collections.emptyList());
    private final ConcertRequestDTO DUMMY_CONCERT_REQUEST_DTO = new ConcertRequestDTO("title", "description", LocalDateTime.of(2021, 7, 21, 1, 0), LocalDateTime.of(2021, 7, 21, 1, 0), "scores");
    private final ConcertProfileDTO DUMMY_CONCERT_PROFILE_DTO = new ConcertProfileDTO(1, "title", "description", LocalDateTime.of(2021, 7, 21, 1, 0), LocalDateTime.of(2021, 7, 21, 1, 0), "scores", null, List.of(new RehersalResponseDTO(1, LocalDateTime.of(2021, 7, 21, 1, 0), "place", "title")), null);
    private final ConcertResponseDTO DUMMY_CONCERT_RESPONSE_DTO = new ConcertResponseDTO(1, "title", "description", LocalDateTime.of(2021, 7, 21, 1, 0), LocalDateTime.of(2021, 7, 21, 1, 0), "scores", List.of(new MusicianConcertResponseDTO("username", "role", BigDecimal.valueOf(100), true, false)), List.of(new RehersalResponseDTO(1, LocalDateTime.of(2021, 7, 21, 1, 0), "place", "title")));


    @Test
    @DisplayName("Test toConcert method")
    void testToConcert() {
        Concert concert = concertMapper.toConcert(DUMMY_CONCERT_REQUEST_DTO);
        assertEquals(DUMMY_CONCERT, concert);
    }

    @Test
    @DisplayName("Test toConcertProfileDTO method with no rehersals")
    void testToConcertProfileDTOWithNoMusiciansAndNoRehersals() {

        DUMMY_CONCERT_PROFILE_DTO.setRehersals(Collections.emptyList());

        when(rehersalMapper.toRehersalResponseDTOs(DUMMY_CONCERT.getRehersals())).thenReturn(Collections.emptyList());
        ConcertProfileDTO concertProfileDTO = concertMapper.toConcertProfileDTO(DUMMY_CONCERT);

        assertEquals(DUMMY_CONCERT_PROFILE_DTO, concertProfileDTO);
    }

    @Test
    @DisplayName("Test toConcertProfileDTO method with rehersals")
    void testToConcertProfileDTOWithNoMusiciansAndRehersals() {

        when(rehersalMapper.toRehersalResponseDTOs(DUMMY_CONCERT.getRehersals())).thenReturn(List.of(new RehersalResponseDTO(1, LocalDateTime.of(2021, 7, 21, 1, 0), "place", "title")));
        ConcertProfileDTO concertProfileDTO = concertMapper.toConcertProfileDTO(DUMMY_CONCERT);

        assertEquals(DUMMY_CONCERT_PROFILE_DTO, concertProfileDTO);
    }

    @Test
    @DisplayName("Test toConcertResponseDTO method with no musicians and no rehersals")
    void testToConcertResponseDTOWithNoMusiciansAndNoRehersals() {

        DUMMY_CONCERT_RESPONSE_DTO.setRehersals(Collections.emptyList());
        DUMMY_CONCERT_RESPONSE_DTO.setMusicians(Collections.emptyList());

        when(rehersalMapper.toRehersalResponseDTOs(DUMMY_CONCERT.getRehersals())).thenReturn(Collections.emptyList());
        when(musicianConcertMapper.toMusicianConcertResponseDTOs(DUMMY_CONCERT.getMusicians())).thenReturn(Collections.emptyList());
        ConcertResponseDTO concertResponseDTO = concertMapper.toConcertResponseDTO(DUMMY_CONCERT);

        assertEquals(DUMMY_CONCERT_RESPONSE_DTO, concertResponseDTO);
    }

    @Test
    @DisplayName("Test toConcertResponseDTO method with no musicians and rehersals")
    void testToConcertResponseDTOWithNoMusiciansAndRehersals() {

        DUMMY_CONCERT_RESPONSE_DTO.setMusicians(Collections.emptyList());

        when(rehersalMapper.toRehersalResponseDTOs(DUMMY_CONCERT.getRehersals())).thenReturn(List.of(new RehersalResponseDTO(1, LocalDateTime.of(2021, 7, 21, 1, 0), "place", "title")));
        when(musicianConcertMapper.toMusicianConcertResponseDTOs(DUMMY_CONCERT.getMusicians())).thenReturn(Collections.emptyList());
        ConcertResponseDTO concertResponseDTO = concertMapper.toConcertResponseDTO(DUMMY_CONCERT);

        assertEquals(DUMMY_CONCERT_RESPONSE_DTO, concertResponseDTO);
    }

    @Test
    @DisplayName("Test toConcertResponseDTO method with musicians and no rehersals")
    void testToConcertResponseDTOWithMusiciansAndNoRehersals() {

        DUMMY_CONCERT_RESPONSE_DTO.setRehersals(Collections.emptyList());

        when(rehersalMapper.toRehersalResponseDTOs(DUMMY_CONCERT.getRehersals())).thenReturn(Collections.emptyList());
        when(musicianConcertMapper.toMusicianConcertResponseDTOs(DUMMY_CONCERT.getMusicians())).thenReturn(List.of(new MusicianConcertResponseDTO("username", "role", BigDecimal.valueOf(100), true, false)));
        ConcertResponseDTO concertResponseDTO = concertMapper.toConcertResponseDTO(DUMMY_CONCERT);

        assertEquals(DUMMY_CONCERT_RESPONSE_DTO, concertResponseDTO);
    }

    @Test
    @DisplayName("Test toConcertResponseDTO method with musicians and rehersals")
    void testToConcertResponseDTOWithMusiciansAndRehersals() {

        when(rehersalMapper.toRehersalResponseDTOs(DUMMY_CONCERT.getRehersals())).thenReturn(List.of(new RehersalResponseDTO(1, LocalDateTime.of(2021, 7, 21, 1, 0), "place", "title")));
        when(musicianConcertMapper.toMusicianConcertResponseDTOs(DUMMY_CONCERT.getMusicians())).thenReturn(List.of(new MusicianConcertResponseDTO("username", "role", BigDecimal.valueOf(100), true, false)));
        ConcertResponseDTO concertResponseDTO = concertMapper.toConcertResponseDTO(DUMMY_CONCERT);

        assertEquals(DUMMY_CONCERT_RESPONSE_DTO, concertResponseDTO);

    }

}
