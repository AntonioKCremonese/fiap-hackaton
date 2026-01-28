package com.devs.fiap_hackaton.controller;

import com.devs.fiap_hackaton.dto.input.PatientCreateDTO;
import com.devs.fiap_hackaton.dto.input.PatientUpdateDTO;
import com.devs.fiap_hackaton.dto.output.PatientOutputDTO;
import com.devs.fiap_hackaton.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientOutputDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{patientId}")
    public PatientOutputDTO getPatientById(@PathVariable String patientId) {
        return patientService.getPatientById(patientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientOutputDTO createPatient(@RequestBody @Valid PatientCreateDTO input) {
        return patientService.createPatient(input);
    }

    @PutMapping("/{patientId}")
    public PatientOutputDTO updatePatient(@PathVariable String patientId, @RequestBody @Valid PatientUpdateDTO input) {
        return patientService.updatePatient(patientId, input);
    }

    @DeleteMapping("/{patientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable String patientId) {
        patientService.deletePatient(patientId);
    }
}