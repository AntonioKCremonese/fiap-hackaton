package com.devs.fiap_hackaton.repository;

import com.devs.fiap_hackaton.model.Appointment;
import com.devs.fiap_hackaton.model.enums.AppointmentStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findByPatientId(String patientId);
    List<Appointment> findByDoctorId(String doctorId);
    List<Appointment> findByStatus(AppointmentStatusEnum status);
    List<Appointment> findByAppointmentDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> findByPatientIdAndStatus(String patientId, AppointmentStatusEnum status);
}