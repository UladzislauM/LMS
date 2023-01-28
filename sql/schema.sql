/*
DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS homeworks;
DROP TABLE IF EXISTS file_links;
DROP TABLE IF EXISTS lessons;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS token_links;
*/
CREATE TABLE IF NOT EXISTS users
(
    id                  BIGSERIAL PRIMARY KEY  NOT NULL,
    email               CHARACTER VARYING(100) NOT NULL,
    "password"          CHARACTER VARYING(150) NOT NULL,
    first_name          CHARACTER VARYING(100) NOT NULL,
    last_name           CHARACTER VARYING(100) NOT NULL,
    patronymic_name     CHARACTER VARYING(100),
    contact_preferences CHARACTER VARYING(50)  NOT NULL,
    social_media        CHARACTER VARYING(50),
    "role"              CHARACTER VARYING(50)  NOT NULL,
    is_active           BOOLEAN                NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS file_links
(
    id   BIGSERIAL NOT NULL,
    link VARCHAR(40),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS courses
(
    id          BIGSERIAL             NOT NULL,
    title       VARCHAR(100)          NOT NULL,
    description VARCHAR(300),
    price       DECIMAL(10, 2)        NOT NULL,
    trainer_id  BIGINT,
    start_date  DATE,
    deleted     BOOLEAN DEFAULT FALSE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (trainer_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS lessons
(
    id          BIGSERIAL             NOT NULL,
    course_id   BIGINT,
    title       VARCHAR(100)          NOT NULL,
    start_time  TIMESTAMP,
    description VARCHAR(300),
    content     VARCHAR(200),
    home_task   VARCHAR(500),
    deleted     BOOLEAN DEFAULT FALSE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (course_id) REFERENCES courses (id)
);

CREATE TABLE IF NOT EXISTS homeworks
(
    id          BIGSERIAL NOT NULL,
    student_id  BIGINT,
    lesson_id   BIGINT,
    comment     VARCHAR(300),
    mark        SMALLINT,
    filelink_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) REFERENCES users (id),
    FOREIGN KEY (lesson_id) REFERENCES lessons (id),
    FOREIGN KEY (filelink_id) REFERENCES file_links (id)
);

CREATE TABLE IF NOT EXISTS requests
(
    id        BIGSERIAL PRIMARY KEY NOT NULL,
    course_id BIGINT REFERENCES courses (id),
    user_id   BIGINT REFERENCES users (id),
    status    CHARACTER VARYING(60)
);

CREATE TABLE IF NOT EXISTS token_links
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    token       CHARACTER VARYING(40) NOT NULL,
    active_time BIGINT,
    create_time TIMESTAMP,
    is_active   BOOLEAN DEFAULT FALSE NOT NULL
);
