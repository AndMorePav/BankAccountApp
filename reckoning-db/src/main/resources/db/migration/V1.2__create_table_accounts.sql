CREATE TABLE IF NOT EXISTS  accounts
(
    id SERIAL not null,
    amount numeric default 0,
	enabled boolean DEFAULT true,
    user_id integer not null,
	PRIMARY KEY (id),
        CONSTRAINT "FK_users" FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);