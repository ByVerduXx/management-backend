package com.ofc.management.model.mapper;

import com.ofc.management.model.Instrument;
import com.ofc.management.model.dto.InstrumentRequestDTO;
import com.ofc.management.model.dto.InstrumentResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InstrumentMapper {

    public Instrument toInstrument(InstrumentRequestDTO instrumentRequestDTO) {
        Instrument instrument = new Instrument();
        instrument.setName(instrumentRequestDTO.getName());
        return instrument;
    }

    public InstrumentResponseDTO toInstrumentResponseDTO(Instrument instrument) {
        InstrumentResponseDTO instrumentResponseDTO = new InstrumentResponseDTO();
        instrumentResponseDTO.setId(instrument.getId());
        instrumentResponseDTO.setName(instrument.getName());
        return instrumentResponseDTO;
    }

    public List<InstrumentResponseDTO> toInstrumentResponseDTOs(List<Instrument> instruments) {
        return instruments.stream()
                .map(this::toInstrumentResponseDTO)
                .toList();
    }
}
