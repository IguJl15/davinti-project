ALTER TABLE users
    ADD complete_name VARCHAR(255);

ALTER TABLE users
    ALTER COLUMN complete_name SET NOT NULL;

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ALTER COLUMN email SET NOT NULL;

ALTER TABLE users
    ALTER COLUMN pass SET NOT NULL;

ALTER TABLE users
    ALTER COLUMN role SET NOT NULL;