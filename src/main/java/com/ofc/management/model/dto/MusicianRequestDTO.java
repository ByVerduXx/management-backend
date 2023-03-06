package com.ofc.management.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MusicianRequestDTO {

    private String name;
    private String lastName;
    private String username;
    private String password;
    private InstrumentRequestDTO instrument;

}
