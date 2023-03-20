package com.ofc.management.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
public class MusicianConcertResponseDTO {
    private String username;
    private String role;
    private BigDecimal payment;
    private boolean accepted;
    private boolean pending;

}
