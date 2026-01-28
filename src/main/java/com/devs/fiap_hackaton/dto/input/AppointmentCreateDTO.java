package com.devs.fiap_hackaton.dto.input;

import com.devs.fiap_hackaton.model.enums.AppointmentTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentCreateDTO {
    @NotBlank(message = "O ID do paciente é obrigatório")
    private String patientId;

    private String doctorId;

    private String examTypeId;

    @NotNull(message = "O tipo de agendamento é obrigatório")
    private AppointmentTypeEnum appointmentType;

    @NotBlank(message = "A data do agendamento é obrigatória")
    private String appointmentDate;

    private String notes;
}