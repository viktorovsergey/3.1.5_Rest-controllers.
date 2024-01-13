DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(300) NOT NULL,
    age      TINYINT      NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
    );
INSERT INTO users(name, age, email, password)
VALUES ('admin',  34, 'admin@gmail.com', '$2a$12$jpwmh02InPLAcMC7P4oLe.yzWBEziks4eZxtrUQ42cvlVG.IuyKfS');
INSERT INTO users(name, age, email, password)
VALUES ('user1',  22, 'user1@gmail.com', '$2a$12$Z3OXF/ThTnORHLNk2.3zUuGjgoDI8M1i/wzW/vZCAUrtlrHV6Cj/C');
INSERT INTO users(name, age, email, password)
VALUES ('user2',  44, 'user2@gmail.com', '$2a$12$Z3OXF/ThTnORHLNk2.3zUuGjgoDI8M1i/wzW/vZCAUrtlrHV6Cj/C');
INSERT INTO users(name, age, email, password)
VALUES ('user3',  26, 'user3@gmail.com', '$2a$12$Z3OXF/ThTnORHLNk2.3zUuGjgoDI8M1i/wzW/vZCAUrtlrHV6Cj/C');



CREATE TABLE IF NOT EXISTS roles
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL
    );
INSERT INTO roles(role_name)
    VALUE ('ROLE_ADMIN');
INSERT INTO roles(role_name)
    VALUE ('ROLE_USER');


CREATE TABLE users_roles
(
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO users_roles(user_id,role_id)
VALUES (1,1);
INSERT INTO users_roles(user_id,role_id)
VALUES (1,2);
INSERT INTO users_roles(user_id,role_id)
VALUES (2,2);
INSERT INTO users_roles(user_id,role_id)
VALUES (3,2);


# DROP TABLE IF EXISTS users_roles;
# DROP TABLE IF EXISTS users;
# DROP TABLE IF EXISTS roles;