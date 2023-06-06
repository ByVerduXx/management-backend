package com.ofc.management.service.exception;

public class AdminCannotDeleteHimself extends RuntimeException {
    public AdminCannotDeleteHimself() {
        super("El administrador no puede eliminarse a sí mismo");
    }
}
