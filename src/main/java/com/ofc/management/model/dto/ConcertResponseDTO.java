package com.ofc.management.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ConcertResponseDTO {

    private Integer id;
    private String title;
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime soundcheck;
    private String scores;
    private List<MusicianConcertResponseDTO> musicians;

    private List<RehersalResponseDTO> rehersals;
}
