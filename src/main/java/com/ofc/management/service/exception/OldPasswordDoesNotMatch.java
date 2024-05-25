package com.ofc.management.service.exception;

public class OldPasswordDoesNotMatch extends RuntimeException {

    public OldPasswordDoesNotMatch() {
        super("La contraseña antigua no coincide");
    }
}
