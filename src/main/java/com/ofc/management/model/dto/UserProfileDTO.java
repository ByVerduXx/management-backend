package com.ofc.management.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class UserProfileDTO {
    private Integer id;
    private String name;
    private String lastName;
    private String username;
    private InstrumentResponseDTO instrument;
    private String role;
    private List<NotificationResponseDTO> notifications;
}
