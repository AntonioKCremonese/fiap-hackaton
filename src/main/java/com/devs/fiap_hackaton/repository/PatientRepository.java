package com.devs.fiap_hackaton.repository;

import com.devs.fiap_hackaton.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    Optional<Patient> findByCpf(String cpf);
    Optional<Patient> findByEmail(String email);
}