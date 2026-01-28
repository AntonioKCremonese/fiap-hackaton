package com.devs.fiap_hackaton.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExamTypeValidationException extends ResponseStatusException {

    private ExamTypeValidationException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public static ExamTypeValidationException examTypeNotFoundException() {
        return new ExamTypeValidationException(HttpStatus.NOT_FOUND, "Tipo de exame não encontrado");
    }

    public static ExamTypeValidationException examTypeAlreadyExistsException(String message) {
        return new ExamTypeValidationException(HttpStatus.CONFLICT, message);
    }
}