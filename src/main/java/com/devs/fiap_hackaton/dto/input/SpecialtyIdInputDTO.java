package com.devs.fiap_hackaton.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialtyIdInputDTO {
    @NotBlank(message = "O ID da especialidade é obrigatório")
    private String specialtyId;
}