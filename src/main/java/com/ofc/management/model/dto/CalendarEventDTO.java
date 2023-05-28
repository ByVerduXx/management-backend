package com.ofc.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CalendarEventDTO {

    private Integer id;
    private String title;
    private LocalDateTime start;
    private String place;
    private String type;
}
