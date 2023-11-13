CREATE SEQUENCE IF NOT EXISTS course_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS instructor_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS student_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS token_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE course
(
    id        INTEGER NOT NULL,
    name      VARCHAR(255),
    is_public BOOLEAN NOT NULL,
    user_id   INTEGER,
    CONSTRAINT pk_course PRIMARY KEY (id)
);

CREATE TABLE enrollment
(
    enrolled_courses_id  INTEGER NOT NULL,
    students_enrolled_id INTEGER NOT NULL
);

CREATE TABLE instructor
(
    id            INTEGER      NOT NULL,
    complete_name VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    pass          VARCHAR(255) NOT NULL,
    role          SMALLINT     NOT NULL,
    siape         VARCHAR(255) NOT NULL,
    CONSTRAINT pk_instructor PRIMARY KEY (id)
);

CREATE TABLE student
(
    id                  INTEGER      NOT NULL,
    complete_name       VARCHAR(255) NOT NULL,
    email               VARCHAR(255) NOT NULL,
    pass                VARCHAR(255) NOT NULL,
    role                SMALLINT     NOT NULL,
    birth_date          TIMESTAMP WITHOUT TIME ZONE,
    registration_number VARCHAR(255) NOT NULL,
    phone_number        VARCHAR(255) NOT NULL,
    CONSTRAINT pk_student PRIMARY KEY (id)
);

CREATE TABLE token
(
    id         INTEGER NOT NULL,
    token      VARCHAR(255),
    token_type VARCHAR(255),
    revoked    BOOLEAN NOT NULL,
    expired    BOOLEAN NOT NULL,
    user_id    INTEGER,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

CREATE TABLE users
(
    id            INTEGER      NOT NULL,
    complete_name VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    pass          VARCHAR(255) NOT NULL,
    role          SMALLINT     NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE instructor
    ADD CONSTRAINT uc_instructor_email UNIQUE (email);

ALTER TABLE student
    ADD CONSTRAINT uc_student_email UNIQUE (email);

ALTER TABLE token
    ADD CONSTRAINT uc_token_token UNIQUE (token);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE course
    ADD CONSTRAINT FK_COURSE_ON_USER FOREIGN KEY (user_id) REFERENCES instructor (id);

ALTER TABLE enrollment
    ADD CONSTRAINT fk_enr_on_course FOREIGN KEY (enrolled_courses_id) REFERENCES course (id);

ALTER TABLE enrollment
    ADD CONSTRAINT fk_enr_on_student FOREIGN KEY (students_enrolled_id) REFERENCES student (id);