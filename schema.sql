-- DROP COMMANDS
DROP TABLE candidate;
DROP VIEW job_request_view;
DROP TABLE job_request;
DROP VIEW employee_view;
DROP TABLE employee;
DROP TABLE role;
DROP TABLE skill;
-------------------------------------------------------------------------------------------------
-- VIEW COMMANDS --
SELECT * FROM role;
SELECT * FROM skill;
SELECT * FROM employee;
SELECT * FROM job_request;
SELECT * FROM job_request_view;
SELECT * FROM candidate;
SELECT * FROM employee_view;

-------------------------------------------------------------------------------------------------
CREATE TABLE role(
    role_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    role VARCHAR2(30)
);

INSERT INTO role(role)
VALUES
('SDE');

-- Team Lead
INSERT INTO role(role)
VALUES
('TL');

-- Project Manager
INSERT INTO role(role)
VALUES
('PM');

-- Business Analyst
INSERT INTO role(role)
VALUES
('BA');

-- HR
INSERT INTO role(role)
VALUES
('HR');

-- Interviwer
INSERT INTO role(role)
VALUES
('INTERVIEWER');

-- Tester
INSERT INTO role(role)
VALUES
('TESTER');

SELECT * FROM role;

-------------------------------------------------------------------------------------------------
CREATE TABLE skill(
    skill_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    skill VARCHAR(30)
);

INSERT INTO skill(skill)
VALUES
('C++');

INSERT INTO skill(skill)
VALUES
('Java');

INSERT INTO skill(skill)
VALUES
('Python');

INSERT INTO skill(skill)
VALUES
('Spring');

INSERT INTO skill(skill)
VALUES
('Reactjs');

INSERT INTO skill(skill)
VALUES
('Statistics');

INSERT INTO skill(skill)
VALUES
('Excel');

SELECT * FROM skill;

-------------------------------------------------------------------------------------------------
CREATE TABLE employee(
    emp_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR2(50),
    password VARCHAR2(50) DEFAULT 'user@123',
    role_id NUMBER(10),
    phone_no NUMBER(20),
    email VARCHAR2(50),
    status VARCHAR2(50),
    skill1_id NUMBER(10),
    skill2_id NUMBER(10),
    skill3_id NUMBER(10),
    CONSTRAINT employee_role_id_fk FOREIGN KEY(role_id) REFERENCES role(role_id),
    CONSTRAINT employee_skill_id_fk1 FOREIGN KEY(skill1_id) REFERENCES skill(skill_id),
    CONSTRAINT employee_skill_id_fk2 FOREIGN KEY(skill2_id) REFERENCES skill(skill_id),
    CONSTRAINT employee_skill_id_fk3 FOREIGN KEY(skill3_id) REFERENCES skill(skill_id)
);

ALTER TABLE employee ADD CONSTRAINT employee_status_ck CHECK(status IN ('active','inactive'));

ALTER TABLE employee MODIFY status DEFAULT 'active'; 
ALTER TABLE employee MODIFY name NOT NULL; 
ALTER TABLE employee MODIFY email UNIQUE NOT NULL;

DESC employee;


INSERT INTO employee(name, role_id, phone_no, email, status, skill1_id, skill2_id, skill3_id)
VALUES 
('gold', 1, 9467348398, 'gold@golddigger.com', 'inactive', 4, 7, 6);

INSERT INTO employee(name, role_id, phone_no, email, status, skill1_id, skill2_id, skill3_id)
VALUES 
('jim', 2, 967348398, 'kim@golddigger.com', 'inactive', 1, 2, 3);

INSERT INTO employee(name, role_id, phone_no, email, status, skill1_id, skill2_id, skill3_id)
VALUES 
('michael', 3, 967348398, 'kim@golddigger.com', 'inactive', 2, 3, 4);

INSERT INTO employee(name, role_id, phone_no, email, status, skill1_id, skill2_id, skill3_id)
VALUES 
('dwight', 4, 967348398, 'kim@golddigger.com', 'inactive', 5, 3, 2);

INSERT INTO employee(name, role_id, phone_no, email, status, skill1_id, skill2_id, skill3_id)
VALUES 
('angela', 5, 967348398, 'kime@golddigger.com', 'inactive', 1,6,7 );

INSERT INTO employee(name, role_id, phone_no, email, status, skill1_id, skill2_id, skill3_id)
VALUES 
('stanley', 6, 967348398, 'kimr@golddigger.com', 'inactive', 3, 5, 6);

INSERT INTO employee(name, role_id, phone_no, email, status, skill1_id, skill2_id, skill3_id)
VALUES 
('kevin', 7, 967348398, 'kimd@golddigger.com', 'inactive', 2, 3, 7);

INSERT INTO employee(name, role_id, phone_no, email, status, skill1_id, skill2_id, skill3_id)
VALUES 
('toby', 2, 967348398, 'kimed@golddigger.com', 'inactive', 4, 3, 2);

INSERT INTO employee(name, role_id, phone_no, email, skill1_id, skill2_id, skill3_id)
VALUES 
('oscar', 3, 9446678398, 'win@golddigger.com', 1, 2, 4);

INSERT INTO employee(name, role_id, phone_no, email, skill1_id, skill3_id)
VALUES 
('arun', 1, 9344516070, 'arunworkacc@golddigger.com', 2, 4, 5);

SELECT * FROM employee;

-------------------------------------------------------------------------------------------------

CREATE TABLE job_request(
    request_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    description VARCHAR2(2000),
    role_id NUMBER(10),
    no_of_vacancies NUMBER(5) DEFAULT 1,
    skill1_id NUMBER(10),
    skill2_id NUMBER(10),
    skill3_id NUMBER(10),
    tl_id NUMBER(10),
    filled NUMBER(10),
    pending NUMBER(10),
    created_date DATE DEFAULT SYSDATE,
    CONSTRAINT job_request_role_id_fk FOREIGN KEY(role_id) REFERENCES role(role_id),
    CONSTRAINT job_request_tl_id_fk FOREIGN KEY(tl_id) REFERENCES employee(emp_id),
    CONSTRAINT job_request_skill_id_fk1 FOREIGN KEY(skill1_id) REFERENCES skill(skill_id),
    CONSTRAINT job_request_skill_id_fk2 FOREIGN KEY(skill2_id) REFERENCES skill(skill_id),
    CONSTRAINT job_request_skill_id_fk3 FOREIGN KEY(skill3_id) REFERENCES skill(skill_id),
    CONSTRAINT job_request_vacancies_ck CHECK(filled + pending = no_of_vacancies)
);

INSERT INTO job_request(description, role_id, no_of_vacancies, skill1_id, tl_id, filled, pending)
VALUES
('passionate and experienced', 1, 5, 1, 2, 3, 2);

INSERT INTO job_request(description, role_id, no_of_vacancies, skill1_id, tl_id, filled, pending)
VALUES
('passionate and experienced', 1, 5, 1, 2, 3, 2);

INSERT INTO job_request(description, role_id, no_of_vacancies, skill1_id, tl_id, filled, pending)
VALUES
('passionate and experienced', 1, 5, 1, 2, 3, 2);

INSERT INTO job_request(description, role_id, no_of_vacancies, skill1_id, tl_id, filled, pending)
VALUES
('passionate and experienced', 1, 5, 1, 2, 3, 2);

INSERT INTO job_request(description, role_id, no_of_vacancies, skill1_id, tl_id, filled, pending)
VALUES
('passionate and experienced', 1, 5, 1, 2, 3, 2);

INSERT INTO job_request(description, role_id, no_of_vacancies, skill1_id, tl_id, filled, pending)
VALUES
('passionate and experienced', 1, 5, 1, 2, 3, 2);

INSERT INTO job_request(description, role_id, no_of_vacancies, skill1_id, tl_id, filled, pending)
VALUES
('passionate and experienced', 1, 5, 1, 2, 3, 2);

INSERT INTO job_request(description, role_id, no_of_vacancies, skill1_id, tl_id, filled, pending)
VALUES
('passionate and experienced', 1, 5, 1, 2, 3, 2);

INSERT INTO job_request(description, role_id, no_of_vacancies, skill1_id, tl_id, filled, pending)
VALUES
('confident', 1, 7, 1, 2, 3, 4);

ALTER TABLE job_request ADD job_req_lvl NUMBER DEFAULT 0;

-- TO BE CHANGED IN FUTURE
ALTER TABLE job_request modify (created_date NULL);

SELECT * FROM job_request;

DESC job_request;
-------------------------------------------------------------------------------------------------------

CREATE TABLE candidate(
    candidate_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email VARCHAR2(50) UNIQUE,
    phone_no NUMBER(20),
    role_id NUMBER(20),
    skill1_id NUMBER(10),
    skill2_id NUMBER(10),
    skill3_id NUMBER(10),
    selected_for_interview VARCHAR(1), -- SELECTED FOR TECHNICAL INTERVIEW? (y/n)
    assessment_status VARCHAR(1), -- SELECTED IN INTERVIEW CONDUCTED BY TECHNICAL PANEL? (y/n)
    confirmation_status VARCHAR(1), -- FINAL CONFIRMATION OF EMPLOYMENT (y/n)?
    request_id NUMBER(10), --FK
    interview_meet_link VARCHAR2(50),
    CONSTRAINT candidate_role_id_fk FOREIGN KEY(role_id) REFERENCES role(role_id),
    CONSTRAINT candidate_skill_id_fk1 FOREIGN KEY(skill1_id) REFERENCES skill(skill_id),
    CONSTRAINT candidate_skill_id_fk2 FOREIGN KEY(skill2_id) REFERENCES skill(skill_id),
    CONSTRAINT candidate_skill_id_fk3 FOREIGN KEY(skill3_id) REFERENCES skill(skill_id),
    CONSTRAINT candidate_selected_for_interview_ck CHECK(selected_for_interview IN ('y','n')),
    CONSTRAINT candidate_assessment_status_ck CHECK(assessment_status IN ('y','n')),
    CONSTRAINT candidate_confirmation_status_ck CHECK(confirmation_status IN ('y','n')),
    CONSTRAINT candiate_request_id_fk FOREIGN KEY(request_id) REFERENCES job_request(request_id)
);

DESC candidate;
-------------------------------------------------------------------------------------------------------
-- Employee view --
CREATE VIEW employee_view AS
SELECT emp_id,name,role_id,phone_no,email, skill1_id, skill2_id, skill3_id, status
FROM employee;
----------------------------------------------------------------------------------
-- Job Request View-----
CREATE VIEW job_request_view AS
SELECT request_id,tl_id,created_date, role_id, description, skill1_id, skill2_id, skill3_id, no_of_vacancies, filled, pending, job_req_lvl
FROM job_request;
