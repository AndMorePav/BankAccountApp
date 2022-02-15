CREATE TABLE IF NOT EXISTS users
(
    id SERIAL not null,
    username varchar not null,
    password varchar not null,
    first_name varchar not null,
    last_name varchar not null,
    enabled boolean DEFAULT true not null,
    role varchar not null,
	PRIMARY KEY (id)
);