package com.devs.fiap_hackaton.controller;

import com.devs.fiap_hackaton.dto.input.DoctorCreateDTO;
import com.devs.fiap_hackaton.dto.input.DoctorUpdateDTO;
import com.devs.fiap_hackaton.dto.output.DoctorOutputDTO;
import com.devs.fiap_hackaton.service.DoctorService;
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
@RequestMapping(path = "/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<DoctorOutputDTO> getAllDoctors(@RequestParam(required = false) String specialtyId) {
        if (specialtyId != null && !specialtyId.isBlank()) {
            return doctorService.getDoctorsBySpecialty(specialtyId);
        }
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{doctorId}")
    public DoctorOutputDTO getDoctorById(@PathVariable String doctorId) {
        return doctorService.getDoctorById(doctorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorOutputDTO createDoctor(@RequestBody @Valid DoctorCreateDTO input) {
        return doctorService.createDoctor(input);
    }

    @PutMapping("/{doctorId}")
    public DoctorOutputDTO updateDoctor(@PathVariable String doctorId, @RequestBody @Valid DoctorUpdateDTO input) {
        return doctorService.updateDoctor(doctorId, input);
    }

    @DeleteMapping("/{doctorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable String doctorId) {
        doctorService.deleteDoctor(doctorId);
    }
}