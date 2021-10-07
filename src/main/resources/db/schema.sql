CREATE TABLE IF NOT EXISTS authorities
(
    id        SERIAL PRIMARY KEY,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE IF NOT EXISTS users
(
    id           SERIAL PRIMARY KEY,
    username     VARCHAR(50)  NOT NULL UNIQUE,
    password     VARCHAR(100) NOT NULL,
    enabled      BOOLEAN DEFAULT true,
    authority_id INT          NOT NULL REFERENCES authorities (id)
);

CREATE TABLE IF NOT EXISTS posts
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(200),
    description VARCHAR(2000),
    created     TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    author_id   INT         NOT NULL REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS comments
(
    id      SERIAL PRIMARY KEY,
    text    VARCHAR(2000),
    post_id INT REFERENCES posts (id),
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    user_id INT         NOT NULL REFERENCES users (id)
);