package com.ofc.management.model.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {

    private String oldPassword;
    private String newPassword;
}
