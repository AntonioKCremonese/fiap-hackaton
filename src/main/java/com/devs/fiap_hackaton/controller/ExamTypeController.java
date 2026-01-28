package com.devs.fiap_hackaton.controller;

import com.devs.fiap_hackaton.dto.input.ExamTypeInputDTO;
import com.devs.fiap_hackaton.dto.output.ExamTypeOutputDTO;
import com.devs.fiap_hackaton.service.ExamTypeService;
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
@RequestMapping(path = "/exam-types")
public class ExamTypeController {

    private final ExamTypeService examTypeService;

    public ExamTypeController(ExamTypeService examTypeService) {
        this.examTypeService = examTypeService;
    }

    @GetMapping
    public List<ExamTypeOutputDTO> getAllExamTypes() {
        return examTypeService.getAllExamTypes();
    }

    @GetMapping("/{examTypeId}")
    public ExamTypeOutputDTO getExamTypeById(@PathVariable String examTypeId) {
        return examTypeService.getExamTypeById(examTypeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExamTypeOutputDTO createExamType(@RequestBody @Valid ExamTypeInputDTO input) {
        return examTypeService.createExamType(input);
    }

    @PutMapping("/{examTypeId}")
    public ExamTypeOutputDTO updateExamType(@PathVariable String examTypeId, @RequestBody @Valid ExamTypeInputDTO input) {
        return examTypeService.updateExamType(examTypeId, input);
    }

    @DeleteMapping("/{examTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExamType(@PathVariable String examTypeId) {
        examTypeService.deleteExamType(examTypeId);
    }
}