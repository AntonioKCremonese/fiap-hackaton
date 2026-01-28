package com.devs.fiap_hackaton.repository;

import com.devs.fiap_hackaton.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, String> {
    Optional<Specialty> findByName(String name);
}