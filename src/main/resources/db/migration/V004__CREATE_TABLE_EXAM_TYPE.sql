CREATE TABLE `exam_type` (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    preparation VARCHAR(500),
    last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_exam_type_name UNIQUE (name)
);