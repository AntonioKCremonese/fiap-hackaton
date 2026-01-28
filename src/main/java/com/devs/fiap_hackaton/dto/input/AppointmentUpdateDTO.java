package com.devs.fiap_hackaton.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentUpdateDTO {
    private String doctorId;

    private String examTypeId;

    @NotBlank(message = "A data do agendamento é obrigatória")
    private String appointmentDate;

    private String notes;
}