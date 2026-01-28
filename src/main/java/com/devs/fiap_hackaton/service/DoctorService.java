package com.devs.fiap_hackaton.service;

import com.devs.fiap_hackaton.dto.input.DoctorCreateDTO;
import com.devs.fiap_hackaton.dto.input.DoctorUpdateDTO;
import com.devs.fiap_hackaton.dto.output.DoctorOutputDTO;
import com.devs.fiap_hackaton.exception.DoctorValidationException;
import com.devs.fiap_hackaton.model.Doctor;
import com.devs.fiap_hackaton.model.Specialty;
import com.devs.fiap_hackaton.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialtyService specialtyService;
    private final ModelMapper modelMapper;

    public DoctorService(DoctorRepository doctorRepository, SpecialtyService specialtyService, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.specialtyService = specialtyService;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public DoctorOutputDTO getDoctorById(String id) {
        Doctor doctor = findById(id);
        return modelMapper.map(doctor, DoctorOutputDTO.class);
    }

    @Transactional(readOnly = true)
    public List<DoctorOutputDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctor -> modelMapper.map(doctor, DoctorOutputDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DoctorOutputDTO> getDoctorsBySpecialty(String specialtyId) {
        return doctorRepository.findBySpecialtyId(specialtyId).stream()
                .map(doctor -> modelMapper.map(doctor, DoctorOutputDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public DoctorOutputDTO createDoctor(DoctorCreateDTO input) {
        doctorRepository.findByCrm(input.getCrm()).ifPresent(doctor -> {
            throw DoctorValidationException.doctorAlreadyExistsException("Já existe um médico cadastrado com este CRM");
        });

        doctorRepository.findByEmail(input.getEmail()).ifPresent(doctor -> {
            throw DoctorValidationException.doctorAlreadyExistsException("Já existe um médico cadastrado com este email");
        });

        Specialty specialty = specialtyService.findById(input.getSpecialty().getSpecialtyId());
        Doctor doctor = modelMapper.map(input, Doctor.class);
        doctor.setSpecialty(specialty);

        return modelMapper.map(doctorRepository.save(doctor), DoctorOutputDTO.class);
    }

    @Transactional
    public DoctorOutputDTO updateDoctor(String id, DoctorUpdateDTO input) {
        Doctor existingDoctor = findById(id);

        doctorRepository.findByEmail(input.getEmail()).ifPresent(doctor -> {
            if (doctor.getEmail().equals(input.getEmail()) && !existingDoctor.equals(doctor)) {
                throw DoctorValidationException.doctorAlreadyExistsException("Já existe um médico cadastrado com este email");
            }
        });

        Specialty specialty = specialtyService.findById(input.getSpecialty().getSpecialtyId());
        modelMapper.map(input, existingDoctor);
        existingDoctor.setSpecialty(specialty);

        return modelMapper.map(doctorRepository.save(existingDoctor), DoctorOutputDTO.class);
    }

    @Transactional
    public void deleteDoctor(String id) {
        doctorRepository.delete(findById(id));
    }

    protected Doctor findById(String id) {
        return doctorRepository.findById(id).orElseThrow(DoctorValidationException::doctorNotFoundException);
    }
}