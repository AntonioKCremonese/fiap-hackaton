package com.devs.fiap_hackaton.dto.output;

import com.devs.fiap_hackaton.model.enums.AppointmentStatusEnum;
import com.devs.fiap_hackaton.model.enums.AppointmentTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentOutputDTO {
    private String id;
    private PatientOutputDTO patient;
    private DoctorOutputDTO doctor;
    private ExamTypeOutputDTO examType;
    private AppointmentTypeEnum appointmentType;
    private String appointmentDate;
    private AppointmentStatusEnum status;
    private String notes;
}