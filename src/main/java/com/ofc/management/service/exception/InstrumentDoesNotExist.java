package com.ofc.management.service.exception;

public class InstrumentDoesNotExist extends RuntimeException {
    public InstrumentDoesNotExist() {
        super("El instrumento no existe");
    }
}
