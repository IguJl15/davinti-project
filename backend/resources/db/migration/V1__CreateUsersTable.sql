CREATE TABLE users
(
    id    VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    pass  VARCHAR(255),
    role  SMALLINT,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);