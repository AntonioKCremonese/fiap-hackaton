package com.devs.fiap_hackaton.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", columnDefinition = "varchar(36)")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "crm", nullable = false, unique = true)
    private String crm;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "specialty_id", referencedColumnName = "id", nullable = false)
    private Specialty specialty;

    @UpdateTimestamp
    @Column(name = "last_update", columnDefinition = "datetime")
    private OffsetDateTime lastUpdate;

    public Doctor(String name, String crm, String email, String phone, Specialty specialty) {
        this.name = name;
        this.crm = crm;
        this.email = email;
        this.phone = phone;
        this.specialty = specialty;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}