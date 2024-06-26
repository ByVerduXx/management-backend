package com.ofc.management.model.mapper;

import com.ofc.management.model.Rehersal;
import com.ofc.management.model.dto.RehersalRequestDTO;
import com.ofc.management.model.dto.RehersalResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RehersalMapper {

    public Rehersal toRehersal(RehersalRequestDTO rehersalRequestDTO) {
        Rehersal rehersal = new Rehersal();

        rehersal.setDate(rehersalRequestDTO.getDate());
        rehersal.setPlace(rehersalRequestDTO.getPlace());

        return rehersal;
    }

    public RehersalResponseDTO toRehersalResponseDTO(Rehersal rehersal) {
        RehersalResponseDTO rehersalResponseDTO = new RehersalResponseDTO();
        rehersalResponseDTO.setId(rehersal.getId());
        rehersalResponseDTO.setDate(rehersal.getDate());
        rehersalResponseDTO.setPlace(rehersal.getPlace());
        rehersalResponseDTO.setConcert(rehersal.getConcert().getTitle());

        return rehersalResponseDTO;
    }

    public List<RehersalResponseDTO> toRehersalResponseDTOs(List<Rehersal> rehersals) {
        return rehersals.stream().map(this::toRehersalResponseDTO).toList();
    }
}
