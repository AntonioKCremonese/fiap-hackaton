package com.devs.fiap_hackaton.repository;

import com.devs.fiap_hackaton.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Optional<Doctor> findByCrm(String crm);
    Optional<Doctor> findByEmail(String email);
    List<Doctor> findBySpecialtyId(String specialtyId);
}