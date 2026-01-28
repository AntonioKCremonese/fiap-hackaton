package com.devs.fiap_hackaton.service;

import com.devs.fiap_hackaton.dto.input.PatientCreateDTO;
import com.devs.fiap_hackaton.dto.input.PatientUpdateDTO;
import com.devs.fiap_hackaton.dto.output.PatientOutputDTO;
import com.devs.fiap_hackaton.exception.PatientValidationException;
import com.devs.fiap_hackaton.model.Patient;
import com.devs.fiap_hackaton.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public PatientService(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public PatientOutputDTO getPatientById(String id) {
        Patient patient = findById(id);
        return modelMapper.map(patient, PatientOutputDTO.class);
    }

    @Transactional(readOnly = true)
    public List<PatientOutputDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patient -> modelMapper.map(patient, PatientOutputDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public PatientOutputDTO createPatient(PatientCreateDTO input) {
        patientRepository.findByCpf(input.getCpf()).ifPresent(patient -> {
            throw PatientValidationException.patientAlreadyExistsException("Já existe um paciente cadastrado com este CPF");
        });

        patientRepository.findByEmail(input.getEmail()).ifPresent(patient -> {
            throw PatientValidationException.patientAlreadyExistsException("Já existe um paciente cadastrado com este email");
        });

        Patient patient = modelMapper.map(input, Patient.class);
        return modelMapper.map(patientRepository.save(patient), PatientOutputDTO.class);
    }

    @Transactional
    public PatientOutputDTO updatePatient(String id, PatientUpdateDTO input) {
        Patient existingPatient = findById(id);

        patientRepository.findByEmail(input.getEmail()).ifPresent(patient -> {
            if (patient.getEmail().equals(input.getEmail()) && !existingPatient.equals(patient)) {
                throw PatientValidationException.patientAlreadyExistsException("Já existe um paciente cadastrado com este email");
            }
        });

        modelMapper.map(input, existingPatient);
        return modelMapper.map(patientRepository.save(existingPatient), PatientOutputDTO.class);
    }

    @Transactional
    public void deletePatient(String id) {
        patientRepository.delete(findById(id));
    }

    protected Patient findById(String id) {
        return patientRepository.findById(id).orElseThrow(PatientValidationException::patientNotFoundException);
    }
}