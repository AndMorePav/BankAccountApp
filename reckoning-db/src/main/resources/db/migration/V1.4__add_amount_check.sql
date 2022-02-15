ALTER TABLE accounts
ADD CHECK (amount >= 0);