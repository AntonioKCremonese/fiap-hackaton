CREATE TABLE `patient` (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(125) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    email VARCHAR(200) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    birth_date VARCHAR(10) NOT NULL,
    address VARCHAR(255),
    last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_patient_cpf UNIQUE (cpf),
    CONSTRAINT uk_patient_email UNIQUE (email)
);