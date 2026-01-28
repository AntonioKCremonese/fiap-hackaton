package com.devs.fiap_hackaton.controller;

import com.devs.fiap_hackaton.dto.input.SpecialtyInputDTO;
import com.devs.fiap_hackaton.dto.output.SpecialtyOutputDTO;
import com.devs.fiap_hackaton.service.SpecialtyService;
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
@RequestMapping(path = "/specialties")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @GetMapping
    public List<SpecialtyOutputDTO> getAllSpecialties() {
        return specialtyService.getAllSpecialties();
    }

    @GetMapping("/{specialtyId}")
    public SpecialtyOutputDTO getSpecialtyById(@PathVariable String specialtyId) {
        return specialtyService.getSpecialtyById(specialtyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SpecialtyOutputDTO createSpecialty(@RequestBody @Valid SpecialtyInputDTO input) {
        return specialtyService.createSpecialty(input);
    }

    @PutMapping("/{specialtyId}")
    public SpecialtyOutputDTO updateSpecialty(@PathVariable String specialtyId, @RequestBody @Valid SpecialtyInputDTO input) {
        return specialtyService.updateSpecialty(specialtyId, input);
    }

    @DeleteMapping("/{specialtyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpecialty(@PathVariable String specialtyId) {
        specialtyService.deleteSpecialty(specialtyId);
    }
}