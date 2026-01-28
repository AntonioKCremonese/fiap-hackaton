package com.devs.fiap_hackaton.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorOutputDTO {
    private String id;
    private String name;
    private String crm;
    private String email;
    private String phone;
    private SpecialtyOutputDTO specialty;
}