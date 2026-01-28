package com.devs.fiap_hackaton.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SpecialtyValidationException extends ResponseStatusException {

    private SpecialtyValidationException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public static SpecialtyValidationException specialtyNotFoundException() {
        return new SpecialtyValidationException(HttpStatus.NOT_FOUND, "Especialidade não encontrada");
    }

    public static SpecialtyValidationException specialtyAlreadyExistsException(String message) {
        return new SpecialtyValidationException(HttpStatus.CONFLICT, message);
    }
}