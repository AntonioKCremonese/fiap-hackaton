package com.devs.fiap_hackaton.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PatientValidationException.class)
    public ResponseEntity<ErrorResponse> handlePatientValidationException(PatientValidationException ex) {
        var error = new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatusCode().value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.valueOf(error.status()));
    }

    @ExceptionHandler(SpecialtyValidationException.class)
    public ResponseEntity<ErrorResponse> handleSpecialtyValidationException(SpecialtyValidationException ex) {
        var error = new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatusCode().value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.valueOf(error.status()));
    }

    @ExceptionHandler(DoctorValidationException.class)
    public ResponseEntity<ErrorResponse> handleDoctorValidationException(DoctorValidationException ex) {
        var error = new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatusCode().value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.valueOf(error.status()));
    }

    @ExceptionHandler(ExamTypeValidationException.class)
    public ResponseEntity<ErrorResponse> handleExamTypeValidationException(ExamTypeValidationException ex) {
        var error = new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatusCode().value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.valueOf(error.status()));
    }

    @ExceptionHandler(AppointmentValidationException.class)
    public ResponseEntity<ErrorResponse> handleAppointmentValidationException(AppointmentValidationException ex) {
        var error = new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatusCode().value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.valueOf(error.status()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}