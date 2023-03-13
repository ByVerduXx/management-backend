package com.ofc.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserUpdateDTO {

    private String name;
    private String lastName;
    private String username;
    private InstrumentRequestDTO instrument;
    private String role;
}
