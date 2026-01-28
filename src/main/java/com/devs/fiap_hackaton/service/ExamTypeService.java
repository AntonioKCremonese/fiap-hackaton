package com.devs.fiap_hackaton.service;

import com.devs.fiap_hackaton.dto.input.ExamTypeInputDTO;
import com.devs.fiap_hackaton.dto.output.ExamTypeOutputDTO;
import com.devs.fiap_hackaton.exception.ExamTypeValidationException;
import com.devs.fiap_hackaton.model.ExamType;
import com.devs.fiap_hackaton.repository.ExamTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamTypeService {

    private final ExamTypeRepository examTypeRepository;
    private final ModelMapper modelMapper;

    public ExamTypeService(ExamTypeRepository examTypeRepository, ModelMapper modelMapper) {
        this.examTypeRepository = examTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public ExamTypeOutputDTO getExamTypeById(String id) {
        ExamType examType = findById(id);
        return modelMapper.map(examType, ExamTypeOutputDTO.class);
    }

    @Transactional(readOnly = true)
    public List<ExamTypeOutputDTO> getAllExamTypes() {
        return examTypeRepository.findAll().stream()
                .map(examType -> modelMapper.map(examType, ExamTypeOutputDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public ExamTypeOutputDTO createExamType(ExamTypeInputDTO input) {
        examTypeRepository.findByName(input.getName()).ifPresent(examType -> {
            throw ExamTypeValidationException.examTypeAlreadyExistsException("Já existe um tipo de exame cadastrado com este nome");
        });

        ExamType examType = modelMapper.map(input, ExamType.class);
        return modelMapper.map(examTypeRepository.save(examType), ExamTypeOutputDTO.class);
    }

    @Transactional
    public ExamTypeOutputDTO updateExamType(String id, ExamTypeInputDTO input) {
        ExamType existingExamType = findById(id);

        examTypeRepository.findByName(input.getName()).ifPresent(examType -> {
            if (examType.getName().equals(input.getName()) && !existingExamType.equals(examType)) {
                throw ExamTypeValidationException.examTypeAlreadyExistsException("Já existe um tipo de exame cadastrado com este nome");
            }
        });

        modelMapper.map(input, existingExamType);
        return modelMapper.map(examTypeRepository.save(existingExamType), ExamTypeOutputDTO.class);
    }

    @Transactional
    public void deleteExamType(String id) {
        examTypeRepository.delete(findById(id));
    }

    protected ExamType findById(String id) {
        return examTypeRepository.findById(id).orElseThrow(ExamTypeValidationException::examTypeNotFoundException);
    }
}