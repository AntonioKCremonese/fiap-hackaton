package com.devs.fiap_hackaton.controller;

import com.devs.fiap_hackaton.dto.input.AppointmentCreateDTO;
import com.devs.fiap_hackaton.dto.input.AppointmentStatusUpdateDTO;
import com.devs.fiap_hackaton.dto.input.AppointmentUpdateDTO;
import com.devs.fiap_hackaton.dto.output.AppointmentOutputDTO;
import com.devs.fiap_hackaton.model.enums.AppointmentStatusEnum;
import com.devs.fiap_hackaton.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<AppointmentOutputDTO> getAllAppointments(
            @RequestParam(required = false) String patientId,
            @RequestParam(required = false) String doctorId,
            @RequestParam(required = false) AppointmentStatusEnum status) {

        if (patientId != null && !patientId.isBlank()) {
            return appointmentService.getAppointmentsByPatient(patientId);
        }

        if (doctorId != null && !doctorId.isBlank()) {
            return appointmentService.getAppointmentsByDoctor(doctorId);
        }

        if (status != null) {
            return appointmentService.getAppointmentsByStatus(status);
        }

        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{appointmentId}")
    public AppointmentOutputDTO getAppointmentById(@PathVariable String appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentOutputDTO createAppointment(@RequestBody @Valid AppointmentCreateDTO input) {
        return appointmentService.createAppointment(input);
    }

    @PutMapping("/{appointmentId}")
    public AppointmentOutputDTO updateAppointment(@PathVariable String appointmentId, @RequestBody @Valid AppointmentUpdateDTO input) {
        return appointmentService.updateAppointment(appointmentId, input);
    }

    @PutMapping("/{appointmentId}/status")
    public AppointmentOutputDTO updateAppointmentStatus(@PathVariable String appointmentId, @RequestBody @Valid AppointmentStatusUpdateDTO input) {
        return appointmentService.updateAppointmentStatus(appointmentId, input);
    }

    @DeleteMapping("/{appointmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable String appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }
}