package com.devs.fiap_hackaton.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", columnDefinition = "varchar(36)")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Column(name = "address")
    private String address;

    @UpdateTimestamp
    @Column(name = "last_update", columnDefinition = "datetime")
    private OffsetDateTime lastUpdate;

    public Patient(String name, String cpf, String email, String phone, String birthDate, String address) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}