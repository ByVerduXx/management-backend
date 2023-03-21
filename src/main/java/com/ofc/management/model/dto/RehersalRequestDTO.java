package com.ofc.management.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class RehersalRequestDTO {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;
    private String place;
    private Integer concert;
}
