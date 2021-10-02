CREATE TABLE authorities
(
    id        SERIAL PRIMARY KEY,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    username     VARCHAR(50)  NOT NULL UNIQUE,
    password     VARCHAR(100) NOT NULL,
    enabled      BOOLEAN DEFAULT true,
    authority_id INT          NOT NULL REFERENCES authorities (id)
);

CREATE TABLE posts
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(200),
    description VARCHAR(2000),
    created     TIMESTAMPTZ NOT NULL DEFAULT now(),
    author_id   INT         NOT NULL REFERENCES users (id)
);

CREATE TABLE comments
(
    id      SERIAL PRIMARY KEY,
    text    VARCHAR(2000),
    post_id INT REFERENCES posts (id),
    created TIMESTAMPTZ NOT NULL DEFAULT now(),
    user_id INT         NOT NULL REFERENCES users (id)
);