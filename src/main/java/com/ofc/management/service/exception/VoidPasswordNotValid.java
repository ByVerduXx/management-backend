package com.ofc.management.service.exception;

public class VoidPasswordNotValid extends RuntimeException {
    public VoidPasswordNotValid() {
        super("La contraseña no puede estar vacía");
    }
}
