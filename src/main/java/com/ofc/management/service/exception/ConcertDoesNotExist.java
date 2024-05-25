package com.ofc.management.service.exception;

public class ConcertDoesNotExist extends RuntimeException {

        public ConcertDoesNotExist() {
            super("El concierto no existe");
        }
}
