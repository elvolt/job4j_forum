DELETE FROM posts;
DELETE FROM users;
DELETE FROM authorities;

INSERT INTO authorities (id, authority)
VALUES (1, 'ROLE_USER');

INSERT INTO users (id, username, enabled, password, authority_id)
VALUES (1, 'root', true, '$2a$10$1jr6MBlUV879JWTxo3jbUuXn80gQTn15/S2ktCEgO.X5oLusJubTK', 1);

INSERT INTO posts (id, name, description, created, author_id)
VALUES (1, 'post1', 'description1', now(), 1);