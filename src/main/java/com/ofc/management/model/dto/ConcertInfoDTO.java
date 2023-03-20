package com.ofc.management.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class ConcertInfoDTO {

    private ConcertResponseDTO concert;

    private List<MusicianConcertResponseDTO> musicians;

    private List<RehersalResponseDTO> rehersals;

}
