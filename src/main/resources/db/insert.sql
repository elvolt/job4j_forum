INSERT INTO authorities (authority)
VALUES ('ROLE_USER');
INSERT INTO authorities (authority)
VALUES ('ROLE_ADMIN');

INSERT INTO users (username, enabled, password, authority_id)
VALUES ('root', true, '$2a$10$1jr6MBlUV879JWTxo3jbUuXn80gQTn15/S2ktCEgO.X5oLusJubTK',
        (SELECT id FROM authorities WHERE authority = 'ROLE_ADMIN'));

INSERT INTO posts (name, author_id)
VALUES ('О чём  этот форум?', (SELECT id FROM users WHERE username = 'root'));
INSERT INTO posts (name, author_id)
VALUES ('Правила форума.', (SELECT id FROM users WHERE username = 'root'));