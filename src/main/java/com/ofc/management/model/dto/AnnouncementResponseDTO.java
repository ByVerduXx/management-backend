package com.ofc.management.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class AnnouncementResponseDTO {

    private Integer id;

    private String title;

    private String content;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime date;

    private String username;

}
