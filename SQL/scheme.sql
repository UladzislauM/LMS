/*
DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS users;
*/

CREATE TABLE IF NOT EXISTS users
(
    id                  BIGSERIAL PRIMARY KEY  NOT NULL,
    email               CHARACTER VARYING(100) NOT NULL,
    password            CHARACTER VARYING(50)  NOT NULL,
    first_name          CHARACTER VARYING(100) NOT NULL,
    last_name           CHARACTER VARYING(100) NOT NULL,
    patronymic_name     CHARACTER VARYING(100),
    contact_preferences CHARACTER VARYING(50)  NOT NULL,
    social_media        CHARACTER VARYING(50),
    role                CHARACTER VARYING(50)  NOT NULL,
    is_active           BOOLEAN                NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS courses(
 id                 BIGSERIAL PRIMARY KEY NOT NULL,
 title				CHARACTER VARYING (80) NOT NULL
 );

CREATE TABLE IF NOT EXISTS requests(
 id                 BIGSERIAL PRIMARY KEY NOT NULL,
 courses_id			BIGINT REFERENCES courses(id),
 users_id			BIGINT REFERENCES users(id),
 status				CHARACTER VARYING (60),
 is_deleted			BOOLEAN NOT NULL DEFAULT FALSE
 );
