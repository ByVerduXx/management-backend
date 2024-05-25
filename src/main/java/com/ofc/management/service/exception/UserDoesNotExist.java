package com.ofc.management.service.exception;

public class UserDoesNotExist extends RuntimeException {
    public UserDoesNotExist() {
        super("El usuario no existe");
    }
}
