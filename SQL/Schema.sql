/*
DROP TABLE IF EXISTS lessons;
DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS users;
*/

CREATE TABLE IF NOT EXISTS users(
 id                 BIGSERIAL PRIMARY KEY NOT NULL,
 name               CHARACTER VARYING (100) NOT NULL,
 last_name          CHARACTER VARYING (100) NOT NULL,
 email              CHARACTER VARYING (100) NOT NULL,
 password           CHARACTER VARYING (50) NOT null,
 role	            CHARACTER VARYING (60),
 is_active			BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS courses(
 id                 BIGSERIAL PRIMARY KEY NOT NULL,
 title				CHARACTER VARYING (80) NOT NULL,
 description		CHARACTER VARYING (200),
 price				DECIMAL(10,2),
 start_date			DATE NOT null,
 trainer			CHARACTER VARYING (60) NOT NULL
 );

CREATE TABLE IF NOT EXISTS requests(
 id                 BIGSERIAL PRIMARY KEY NOT NULL,
 courses_id			BIGINT REFERENCES courses(id),
 users_id			BIGINT REFERENCES users(id),
 status				CHARACTER VARYING (60),
 is_deleted			BOOLEAN NOT NULL DEFAULT FALSE
 );
 
 CREATE TABLE IF NOT EXISTS lessons(
 id                 BIGSERIAL PRIMARY KEY NOT NULL,
 title				CHARACTER VARYING (80) NOT NULL,
 courses_id			BIGINT REFERENCES courses(id)
 );
