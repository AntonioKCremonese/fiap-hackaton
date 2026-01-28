CREATE TABLE `doctor` (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(125) NOT NULL,
    crm VARCHAR(20) NOT NULL,
    email VARCHAR(200) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    specialty_id CHAR(36) NOT NULL,
    last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_doctor_specialty FOREIGN KEY (specialty_id) REFERENCES specialty(id),
    CONSTRAINT uk_doctor_crm UNIQUE (crm),
    CONSTRAINT uk_doctor_email UNIQUE (email)
);