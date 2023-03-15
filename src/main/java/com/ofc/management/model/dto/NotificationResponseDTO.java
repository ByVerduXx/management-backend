package com.ofc.management.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class NotificationResponseDTO {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime date;
}
