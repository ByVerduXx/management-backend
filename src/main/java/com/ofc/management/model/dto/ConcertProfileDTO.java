package com.ofc.management.model.dto;

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
public class ConcertProfileDTO {

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime date;
    private LocalDateTime soundcheck;
    private String scores;
    private List<String> musicians;
    private List<RehersalResponseDTO> rehersals;
    private MusicianConcertProfileDTO musicianInfo;

}
