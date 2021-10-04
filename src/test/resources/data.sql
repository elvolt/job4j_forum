INSERT INTO authorities (authority)
VALUES ('ROLE_USER');
INSERT INTO authorities (authority)
VALUES ('ROLE_ADMIN');

INSERT INTO users (username, enabled, password, authority_id)
VALUES ('root', true, '$2a$10$1jr6MBlUV879JWTxo3jbUuXn80gQTn15/S2ktCEgO.X5oLusJubTK', 1);

INSERT INTO posts (name, description, created, author_id)
VALUES ('post1', 'description1', now(), 1);