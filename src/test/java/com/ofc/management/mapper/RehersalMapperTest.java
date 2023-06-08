package com.ofc.management.mapper;

import com.ofc.management.model.Concert;
import com.ofc.management.model.Rehersal;
import com.ofc.management.model.dto.RehersalRequestDTO;
import com.ofc.management.model.dto.RehersalResponseDTO;
import com.ofc.management.model.mapper.RehersalMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RehersalMapperTest {

    private final RehersalMapper rehersalMapper = new RehersalMapper();

    private final Concert DUMMY_CONCERT = new Concert(1, "title", "description", "place", LocalDateTime.of(2022, 1, 1, 1, 1), LocalDateTime.of(2022, 1, 1, 1, 1), "scores", null, null);
    private final Rehersal DUMMY_REHERSAL = new Rehersal(1, LocalDateTime.of(2021, 1, 1, 1, 1), "place", DUMMY_CONCERT);
    private final RehersalRequestDTO DUMMY_REHERSAL_REQUEST_DTO = new RehersalRequestDTO(LocalDateTime.of(2021, 1, 1, 1, 1), "place", 1);
    private final RehersalResponseDTO DUMMY_REHERSAL_RESPONSE_DTO = new RehersalResponseDTO(1, LocalDateTime.of(2021, 1, 1, 1, 1), "place", "title");

    @Test
    @DisplayName("Test toRehersal method")
    void testToRehersal() {

        Rehersal rehersal = rehersalMapper.toRehersal(DUMMY_REHERSAL_REQUEST_DTO);

        assertEquals(DUMMY_REHERSAL.getDate(), rehersal.getDate());
        assertEquals(DUMMY_REHERSAL.getPlace(), rehersal.getPlace());
    }

    @Test
    @DisplayName("Test toRehersalResponseDTO method")
    void testToRehersalResponseDTO() {

        RehersalResponseDTO rehersalResponseDTO = rehersalMapper.toRehersalResponseDTO(DUMMY_REHERSAL);

        assertEquals(DUMMY_REHERSAL_RESPONSE_DTO, rehersalResponseDTO);
    }

    @Test
    @DisplayName("Test toRehersalResponseDTOs method")
    void testToRehersalResponseDTOs() {

        List<RehersalResponseDTO> rehersalResponseDTOs = rehersalMapper.toRehersalResponseDTOs(List.of(DUMMY_REHERSAL));

        assertEquals(1, rehersalResponseDTOs.size());
        assertEquals(DUMMY_REHERSAL_RESPONSE_DTO, rehersalResponseDTOs.get(0));
    }

}
