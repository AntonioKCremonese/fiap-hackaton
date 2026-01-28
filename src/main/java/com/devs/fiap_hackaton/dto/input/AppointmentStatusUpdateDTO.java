package com.devs.fiap_hackaton.dto.input;

import com.devs.fiap_hackaton.model.enums.AppointmentStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentStatusUpdateDTO {
    @NotNull(message = "O status é obrigatório")
    private AppointmentStatusEnum status;
}