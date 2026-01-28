package com.devs.fiap_hackaton.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientUpdateDTO {
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 125, message = "O nome deve ter no máximo 125 caracteres")
    private String name;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    @Size(max = 200, message = "O email deve ter no máximo 200 caracteres")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String phone;

    @Size(max = 255, message = "O endereço deve ter no máximo 255 caracteres")
    private String address;
}