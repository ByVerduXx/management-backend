package com.ofc.management.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode
public class ConcertResponseDTO {

    private Integer id;
    private String title;
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime soundcheck;

    private List<MusicianConcertResponseDTO> musicians;

    private List<RehersalResponseDTO> rehersals;
}
