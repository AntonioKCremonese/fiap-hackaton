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
@Table(name = "exam_type")
public class ExamType {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", columnDefinition = "varchar(36)")
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "preparation")
    private String preparation;

    @UpdateTimestamp
    @Column(name = "last_update", columnDefinition = "datetime")
    private OffsetDateTime lastUpdate;

    public ExamType(String name, String description, String preparation) {
        this.name = name;
        this.description = description;
        this.preparation = preparation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ExamType examType = (ExamType) o;
        return Objects.equals(id, examType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}