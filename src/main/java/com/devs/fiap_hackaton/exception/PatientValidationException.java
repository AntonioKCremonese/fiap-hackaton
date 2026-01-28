package com.devs.fiap_hackaton.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PatientValidationException extends ResponseStatusException {

    private PatientValidationException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public static PatientValidationException patientNotFoundException() {
        return new PatientValidationException(HttpStatus.NOT_FOUND, "Paciente não encontrado");
    }

    public static PatientValidationException patientAlreadyExistsException(String message) {
        return new PatientValidationException(HttpStatus.CONFLICT, message);
    }
}