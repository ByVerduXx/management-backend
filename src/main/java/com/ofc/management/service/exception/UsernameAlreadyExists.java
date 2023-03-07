package com.ofc.management.service.exception;

public class UsernameAlreadyExists extends RuntimeException {
    public UsernameAlreadyExists() {
        super("El nombre de usuario ya existe");
    }
}
