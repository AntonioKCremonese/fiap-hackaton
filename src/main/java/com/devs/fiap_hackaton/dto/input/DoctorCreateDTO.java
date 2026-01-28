package com.devs.fiap_hackaton.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorCreateDTO {
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 125, message = "O nome deve ter no máximo 125 caracteres")
    private String name;

    @NotBlank(message = "O CRM é obrigatório")
    @Size(max = 20, message = "O CRM deve ter no máximo 20 caracteres")
    private String crm;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    @Size(max = 200, message = "O email deve ter no máximo 200 caracteres")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String phone;

    @NotNull(message = "A especialidade é obrigatória")
    @Valid
    private SpecialtyIdInputDTO specialty;
}