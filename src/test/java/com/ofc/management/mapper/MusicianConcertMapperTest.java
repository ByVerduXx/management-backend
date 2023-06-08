package com.ofc.management.mapper;

import com.ofc.management.model.Concert;
import com.ofc.management.model.MusicianConcert;
import com.ofc.management.model.MusicianConcertPK;
import com.ofc.management.model.User;
import com.ofc.management.model.dto.MusicianConcertRequestDTO;
import com.ofc.management.model.dto.MusicianConcertResponseDTO;
import com.ofc.management.model.mapper.MusicianConcertMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MusicianConcertMapperTest {

    private final MusicianConcertMapper musicianConcertMapper = new MusicianConcertMapper();

    private final User DUMMY_USER = new User(1, "Name", "LastName", "Username", "Password", "Role", null);
    private final Concert DUMMY_CONCERT = new Concert(1, "title", "description", "place", LocalDateTime.of(2021, 7, 21, 1, 0), LocalDateTime.of(2021, 7, 21, 1, 0), "scores", Collections.emptyList(), Collections.emptyList());
    private final MusicianConcert DUMMY_MUSICIAN_CONCERT = new MusicianConcert(new MusicianConcertPK(1, 1), DUMMY_USER, DUMMY_CONCERT, "Role", BigDecimal.valueOf(100), true, false);
    private final MusicianConcertRequestDTO DUMMY_MUSICIAN_CONCERT_REQUEST_DTO = new MusicianConcertRequestDTO(1, 1, BigDecimal.valueOf(100),"Role", true, false);
    private final MusicianConcertResponseDTO DUMMY_MUSICIAN_CONCERT_RESPONSE_DTO = new MusicianConcertResponseDTO("Username", "Role", BigDecimal.valueOf(100), true, false);

    @Test
    @DisplayName("Test toMusicianConcert method")
    void testToMusicianConcert() {

        MusicianConcert musicianConcert = musicianConcertMapper.toMusicianConcert(DUMMY_MUSICIAN_CONCERT_REQUEST_DTO);

        assertEquals(DUMMY_MUSICIAN_CONCERT.getId().getMusicianId(), musicianConcert.getId().getMusicianId());
        assertEquals(DUMMY_MUSICIAN_CONCERT.getId().getConcertId(), musicianConcert.getId().getConcertId());
        assertEquals(DUMMY_MUSICIAN_CONCERT.getRole(), musicianConcert.getRole());
        assertEquals(DUMMY_MUSICIAN_CONCERT.getPayment(), musicianConcert.getPayment());
        assertEquals(DUMMY_MUSICIAN_CONCERT.isAccepted(), musicianConcert.isAccepted());
        assertEquals(DUMMY_MUSICIAN_CONCERT.isPending(), musicianConcert.isPending());
    }

    @Test
    @DisplayName("Test toMusicianConcerts method")
    void testToMusicianConcerts() {

        List<MusicianConcert> musicianConcerts = musicianConcertMapper.toMusicianConcerts(List.of(DUMMY_MUSICIAN_CONCERT_REQUEST_DTO));

        assertEquals(1, musicianConcerts.size());
        assertEquals(DUMMY_MUSICIAN_CONCERT.getId().getMusicianId(), musicianConcerts.get(0).getId().getMusicianId());
        assertEquals(DUMMY_MUSICIAN_CONCERT.getId().getConcertId(), musicianConcerts.get(0).getId().getConcertId());
        assertEquals(DUMMY_MUSICIAN_CONCERT.getRole(), musicianConcerts.get(0).getRole());
        assertEquals(DUMMY_MUSICIAN_CONCERT.getPayment(), musicianConcerts.get(0).getPayment());
        assertEquals(DUMMY_MUSICIAN_CONCERT.isAccepted(), musicianConcerts.get(0).isAccepted());
        assertEquals(DUMMY_MUSICIAN_CONCERT.isPending(), musicianConcerts.get(0).isPending());
    }

    @Test
    @DisplayName("Test toMusicianConcertResponseDTO method")
    void testToMusicianConcertResponseDTO() {

        MusicianConcertResponseDTO musicianConcertResponseDTO = musicianConcertMapper.toMusicianConcertResponseDTO(DUMMY_MUSICIAN_CONCERT);

        assertEquals(DUMMY_MUSICIAN_CONCERT_RESPONSE_DTO, musicianConcertResponseDTO);
    }
}
