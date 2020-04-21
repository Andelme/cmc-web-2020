CREATE TYPE DEGREE_TYPE AS ENUM ('without_degree', 'bachelor', 'master', 'doctor');

CREATE TABLE IF NOT EXISTS WORKER (
    worker_id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    birth_date DATE NOT NULL,
    address VARCHAR(200) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    hire_date DATE,
    education_degree DEGREE_TYPE NOT NULL
);
CREATE TABLE IF NOT EXISTS POSITION_TYPE (
    postype_id SERIAL PRIMARY KEY,
    postype_name VARCHAR(40) NOT NULL,
    responsibilities VARCHAR(800) NOT NULL,
    salary INT NOT NULL
);
CREATE TABLE IF NOT EXISTS DEPARTMENT (
    department_id SERIAL PRIMARY KEY,
    department_name VARCHAR(40) NOT NULL UNIQUE,
    head_department INT REFERENCES DEPARTMENT ON DELETE SET NULL
);
CREATE TABLE IF NOT EXISTS WORK_POSITION (
    position_id SERIAL PRIMARY KEY,
    worker_id INT REFERENCES WORKER ON DELETE SET NULL,
    postype_id INT REFERENCES POSITION_TYPE ON DELETE CASCADE NOT NULL,
    appointment_date DATE,
    retire_date DATE,
    work_rate NUMERIC(4, 3) NOT NULL,
    department_id INT REFERENCES DEPARTMENT ON DELETE CASCADE NOT NULL
);