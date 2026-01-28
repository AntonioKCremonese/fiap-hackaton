CREATE TABLE `appointment` (
    id CHAR(36) NOT NULL PRIMARY KEY,
    patient_id CHAR(36) NOT NULL,
    doctor_id CHAR(36),
    exam_type_id CHAR(36),
    appointment_type VARCHAR(30) NOT NULL,
    appointment_date DATETIME NOT NULL,
    status VARCHAR(30) NOT NULL,
    notes TEXT,
    last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_appointment_patient FOREIGN KEY (patient_id) REFERENCES patient(id),
    CONSTRAINT fk_appointment_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    CONSTRAINT fk_appointment_exam_type FOREIGN KEY (exam_type_id) REFERENCES exam_type(id)
);