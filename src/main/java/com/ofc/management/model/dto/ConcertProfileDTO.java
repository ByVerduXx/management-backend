package com.ofc.management.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode
public class ConcertProfileDTO {

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime date;
    private LocalDateTime soundcheck;
    private List<String> musicians;
    private List<RehersalResponseDTO> rehersals;
    private MusicianConcertProfileDTO musicianInfo;

}
