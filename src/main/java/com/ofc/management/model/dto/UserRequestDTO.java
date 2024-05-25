package com.ofc.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String name;
    private String lastName;
    private String username;
    private String password;
    private InstrumentRequestDTO instrument;
    private String role;
}
