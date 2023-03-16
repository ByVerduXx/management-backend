package com.ofc.management.service.exception;

public class AnnouncementDoesNotExist extends RuntimeException {
    public AnnouncementDoesNotExist() {
        super("El anuncio no existe");
    }
}
