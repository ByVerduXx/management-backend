package com.ofc.management.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class RehersalResponseDTO {

    private Integer id;
    private LocalDateTime date;
    private String place;
    private String concert;
}
