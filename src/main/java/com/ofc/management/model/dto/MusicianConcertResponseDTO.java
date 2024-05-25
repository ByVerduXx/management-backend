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
public class MusicianConcertResponseDTO {
    private Integer id;
    private String username;
    private String role;
    private BigDecimal payment;
    private boolean accepted;
    private boolean pending;

}
