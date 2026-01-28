package com.devs.fiap_hackaton.service;

import com.devs.fiap_hackaton.dto.input.AppointmentCreateDTO;
import com.devs.fiap_hackaton.dto.input.AppointmentStatusUpdateDTO;
import com.devs.fiap_hackaton.dto.input.AppointmentUpdateDTO;
import com.devs.fiap_hackaton.dto.output.AppointmentOutputDTO;
import com.devs.fiap_hackaton.exception.AppointmentValidationException;
import com.devs.fiap_hackaton.model.Appointment;
import com.devs.fiap_hackaton.model.Doctor;
import com.devs.fiap_hackaton.model.ExamType;
import com.devs.fiap_hackaton.model.Patient;
import com.devs.fiap_hackaton.model.enums.AppointmentStatusEnum;
import com.devs.fiap_hackaton.model.enums.AppointmentTypeEnum;
import com.devs.fiap_hackaton.repository.AppointmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ExamTypeService examTypeService;
    private final ModelMapper modelMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientService patientService,
                              DoctorService doctorService, ExamTypeService examTypeService, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.examTypeService = examTypeService;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public AppointmentOutputDTO getAppointmentById(String id) {
        Appointment appointment = findById(id);
        return modelMapper.map(appointment, AppointmentOutputDTO.class);
    }

    @Transactional(readOnly = true)
    public List<AppointmentOutputDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentOutputDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AppointmentOutputDTO> getAppointmentsByPatient(String patientId) {
        return appointmentRepository.findByPatientId(patientId).stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentOutputDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AppointmentOutputDTO> getAppointmentsByDoctor(String doctorId) {
        return appointmentRepository.findByDoctorId(doctorId).stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentOutputDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AppointmentOutputDTO> getAppointmentsByStatus(AppointmentStatusEnum status) {
        return appointmentRepository.findByStatus(status).stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentOutputDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public AppointmentOutputDTO createAppointment(AppointmentCreateDTO input) {
        Patient patient = patientService.findById(input.getPatientId());

        Doctor doctor = null;
        ExamType examType = null;

        if (input.getAppointmentType() == AppointmentTypeEnum.CONSULTATION) {
            if (input.getDoctorId() == null || input.getDoctorId().isBlank()) {
                throw AppointmentValidationException.invalidAppointmentTypeException();
            }
            doctor = doctorService.findById(input.getDoctorId());
        } else if (input.getAppointmentType() == AppointmentTypeEnum.EXAM) {
            if (input.getExamTypeId() == null || input.getExamTypeId().isBlank()) {
                throw AppointmentValidationException.invalidAppointmentTypeException();
            }
            examType = examTypeService.findById(input.getExamTypeId());
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setExamType(examType);
        appointment.setAppointmentType(input.getAppointmentType());
        appointment.setAppointmentDate(LocalDateTime.parse(input.getAppointmentDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        appointment.setStatus(AppointmentStatusEnum.SCHEDULED);
        appointment.setNotes(input.getNotes());

        return modelMapper.map(appointmentRepository.save(appointment), AppointmentOutputDTO.class);
    }

    @Transactional
    public AppointmentOutputDTO updateAppointment(String id, AppointmentUpdateDTO input) {
        Appointment existingAppointment = findById(id);

        if (existingAppointment.getStatus() == AppointmentStatusEnum.COMPLETED ||
            existingAppointment.getStatus() == AppointmentStatusEnum.CANCELLED) {
            throw AppointmentValidationException.invalidStatusTransitionException("Não é possível atualizar agendamentos concluídos ou cancelados");
        }

        if (input.getDoctorId() != null && !input.getDoctorId().isBlank()) {
            Doctor doctor = doctorService.findById(input.getDoctorId());
            existingAppointment.setDoctor(doctor);
        }

        if (input.getExamTypeId() != null && !input.getExamTypeId().isBlank()) {
            ExamType examType = examTypeService.findById(input.getExamTypeId());
            existingAppointment.setExamType(examType);
        }

        existingAppointment.setAppointmentDate(LocalDateTime.parse(input.getAppointmentDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        existingAppointment.setNotes(input.getNotes());

        return modelMapper.map(appointmentRepository.save(existingAppointment), AppointmentOutputDTO.class);
    }

    @Transactional
    public AppointmentOutputDTO updateAppointmentStatus(String id, AppointmentStatusUpdateDTO input) {
        Appointment appointment = findById(id);

        if (appointment.getStatus() == AppointmentStatusEnum.COMPLETED) {
            throw AppointmentValidationException.invalidStatusTransitionException("Não é possível alterar o status de agendamentos concluídos");
        }

        appointment.updateStatus(input.getStatus());
        return modelMapper.map(appointmentRepository.save(appointment), AppointmentOutputDTO.class);
    }

    @Transactional
    public void deleteAppointment(String id) {
        Appointment appointment = findById(id);

        if (appointment.getStatus() == AppointmentStatusEnum.COMPLETED) {
            throw AppointmentValidationException.invalidStatusTransitionException("Não é possível deletar agendamentos concluídos");
        }

        appointmentRepository.delete(appointment);
    }

    protected Appointment findById(String id) {
        return appointmentRepository.findById(id).orElseThrow(AppointmentValidationException::appointmentNotFoundException);
    }
}