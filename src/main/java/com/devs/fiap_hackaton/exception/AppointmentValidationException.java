package com.devs.fiap_hackaton.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AppointmentValidationException extends ResponseStatusException {

    private AppointmentValidationException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public static AppointmentValidationException appointmentNotFoundException() {
        return new AppointmentValidationException(HttpStatus.NOT_FOUND, "Agendamento não encontrado");
    }

    public static AppointmentValidationException invalidAppointmentTypeException() {
        return new AppointmentValidationException(HttpStatus.BAD_REQUEST, "Tipo de agendamento inválido. Médico é obrigatório para consultas e tipo de exame é obrigatório para exames");
    }

    public static AppointmentValidationException invalidStatusTransitionException(String message) {
        return new AppointmentValidationException(HttpStatus.BAD_REQUEST, message);
    }
}