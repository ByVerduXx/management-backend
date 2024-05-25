package com.ofc.management.service.exception;

public class MusicianConcertDoesNotExist extends RuntimeException{
    public MusicianConcertDoesNotExist() {
        super("No existe ese músico en ese concierto");
    }
}
