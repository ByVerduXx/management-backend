package com.ofc.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MusicianConcertRequestDTO {
    private Integer userId;
    private Integer concertId;
    private BigDecimal payment;
    private String role;
    private boolean accepted;
    private boolean pending;
}
