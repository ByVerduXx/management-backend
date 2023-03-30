package com.ofc.management.mapper;

import com.ofc.management.model.Instrument;
import com.ofc.management.model.dto.InstrumentRequestDTO;
import com.ofc.management.model.dto.InstrumentResponseDTO;
import com.ofc.management.model.mapper.InstrumentMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstrumentMapperTest {
    private final InstrumentMapper instrumentMapper = new InstrumentMapper();
    private final Instrument DUMMY_INSTRUMENT = new Instrument(1, "Instrument");
    private final InstrumentRequestDTO DUMMY_INSTRUMENT_REQUEST_DTO = new InstrumentRequestDTO("Instrument");
    private final InstrumentResponseDTO DUMMY_INSTRUMENT_RESPONSE_DTO = new InstrumentResponseDTO(1, "Instrument");

    @Test
    @DisplayName("Test toInstrument method")
    void testToInstrument() {
        Instrument instrument = instrumentMapper.toInstrument(DUMMY_INSTRUMENT_REQUEST_DTO);
        assertEquals(DUMMY_INSTRUMENT, instrument);
    }

    @Test
    @DisplayName("Test toInstrumentResponseDTO method")
    void testToInstrumentResponseDTO() {
        InstrumentResponseDTO instrumentResponseDTO = instrumentMapper.toInstrumentResponseDTO(DUMMY_INSTRUMENT);
        assertEquals(DUMMY_INSTRUMENT_RESPONSE_DTO, instrumentResponseDTO);
    }
}
