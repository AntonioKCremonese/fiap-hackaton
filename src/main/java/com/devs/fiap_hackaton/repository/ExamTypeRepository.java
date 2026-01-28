package com.devs.fiap_hackaton.repository;

import com.devs.fiap_hackaton.model.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamTypeRepository extends JpaRepository<ExamType, String> {
    Optional<ExamType> findByName(String name);
}