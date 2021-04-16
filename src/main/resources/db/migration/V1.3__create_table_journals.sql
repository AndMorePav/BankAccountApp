CREATE TABLE IF NOT EXISTS  journals
(
    id SERIAL not null,
	operation varchar not null,
    initial_amount numeric default 0,
	final_amount numeric default 0,
	operation_time timestamptz not null,
    account_id integer,
		PRIMARY KEY (id),
        CONSTRAINT "FK_accounts" FOREIGN KEY (account_id)
        REFERENCES accounts (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);