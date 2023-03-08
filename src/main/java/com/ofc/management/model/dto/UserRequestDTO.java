package com.ofc.management.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserRequestDTO {

    private String name;
    private String lastName;
    private String username;
    private String password;
    private InstrumentRequestDTO instrument;
    private String role;

    //TODO: make another dto for user update

}
