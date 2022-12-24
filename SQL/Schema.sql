/*
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