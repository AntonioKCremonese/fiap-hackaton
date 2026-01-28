package com.devs.fiap_hackaton.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DoctorValidationException extends ResponseStatusException {

    private DoctorValidationException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public static DoctorValidationException doctorNotFoundException() {
        return new DoctorValidationException(HttpStatus.NOT_FOUND, "Médico não encontrado");
    }

    public static DoctorValidationException doctorAlreadyExistsException(String message) {
        return new DoctorValidationException(HttpStatus.CONFLICT, message);
    }
}