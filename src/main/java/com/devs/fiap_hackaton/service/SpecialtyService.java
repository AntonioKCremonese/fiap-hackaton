package com.devs.fiap_hackaton.service;

import com.devs.fiap_hackaton.dto.input.SpecialtyInputDTO;
import com.devs.fiap_hackaton.dto.output.SpecialtyOutputDTO;
import com.devs.fiap_hackaton.exception.SpecialtyValidationException;
import com.devs.fiap_hackaton.model.Specialty;
import com.devs.fiap_hackaton.repository.SpecialtyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final ModelMapper modelMapper;

    public SpecialtyService(SpecialtyRepository specialtyRepository, ModelMapper modelMapper) {
        this.specialtyRepository = specialtyRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public SpecialtyOutputDTO getSpecialtyById(String id) {
        Specialty specialty = findById(id);
        return modelMapper.map(specialty, SpecialtyOutputDTO.class);
    }

    @Transactional(readOnly = true)
    public List<SpecialtyOutputDTO> getAllSpecialties() {
        return specialtyRepository.findAll().stream()
                .map(specialty -> modelMapper.map(specialty, SpecialtyOutputDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public SpecialtyOutputDTO createSpecialty(SpecialtyInputDTO input) {
        specialtyRepository.findByName(input.getName()).ifPresent(specialty -> {
            throw SpecialtyValidationException.specialtyAlreadyExistsException("Já existe uma especialidade cadastrada com este nome");
        });

        Specialty specialty = modelMapper.map(input, Specialty.class);
        return modelMapper.map(specialtyRepository.save(specialty), SpecialtyOutputDTO.class);
    }

    @Transactional
    public SpecialtyOutputDTO updateSpecialty(String id, SpecialtyInputDTO input) {
        Specialty existingSpecialty = findById(id);

        specialtyRepository.findByName(input.getName()).ifPresent(specialty -> {
            if (specialty.getName().equals(input.getName()) && !existingSpecialty.equals(specialty)) {
                throw SpecialtyValidationException.specialtyAlreadyExistsException("Já existe uma especialidade cadastrada com este nome");
            }
        });

        modelMapper.map(input, existingSpecialty);
        return modelMapper.map(specialtyRepository.save(existingSpecialty), SpecialtyOutputDTO.class);
    }

    @Transactional
    public void deleteSpecialty(String id) {
        specialtyRepository.delete(findById(id));
    }

    protected Specialty findById(String id) {
        return specialtyRepository.findById(id).orElseThrow(SpecialtyValidationException::specialtyNotFoundException);
    }
}