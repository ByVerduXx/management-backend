package com.ofc.management.service.exception;

public class NoDateProvided extends RuntimeException{
    public NoDateProvided() {
        super("No se ha proporcionado una fecha");
    }
}
