package com.ofc.management.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
public class MusicianConcertRequestDTO {
    private Integer userId;
    private Integer concertId;
    private BigDecimal payment;
    private String role;
    private boolean accepted;
    private boolean pending;
}
