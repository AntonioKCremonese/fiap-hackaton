package com.devs.fiap_hackaton.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamTypeInputDTO {
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String name;

    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    private String description;

    @Size(max = 500, message = "A preparação deve ter no máximo 500 caracteres")
    private String preparation;
}