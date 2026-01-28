package com.devs.fiap_hackaton.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientOutputDTO {
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String birthDate;
    private String address;
}