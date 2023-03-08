package com.ofc.management.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserResponseDTO {
    private Integer id;
    private String name;
    private String lastName;
    private String username;
    private InstrumentResponseDTO instrument;
    private String role;

}
