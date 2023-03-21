package com.ofc.management.service.exception;

public class TitleCannotBeVoid extends RuntimeException {

        public TitleCannotBeVoid() {
            super("El título no puede ser nulo o vacío");
        }
}
