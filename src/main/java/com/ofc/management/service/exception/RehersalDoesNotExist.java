package com.ofc.management.service.exception;

public class RehersalDoesNotExist extends RuntimeException{
    public RehersalDoesNotExist() {
        super("El ensayo no existe");
    }
}
