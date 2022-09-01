DROP DATABASE IF EXISTS provider;

CREATE DATABASE IF NOT EXISTS provider;

use provider;

CREATE TABLE users(
                      id INT NOT NULL AUTO_INCREMENT,
                      login VARCHAR(45) NOT NULL,
                      name VARCHAR(45) NOT NULL,
                      surname  VARCHAR(45) NOT NULL,
                      password VARCHAR(45) NOT NULL,
                      admin BOOLEAN NOT NULL DEFAULT TRUE,
                      balance DOUBLE DEFAULT 0.00,
                      status BOOLEAN NOT NULL DEFAULT TRUE,
                      primary key(id)
);

CREATE TABLE services(
                         id INT NOT NULL AUTO_INCREMENT,
                         name VARCHAR(45) NOT NULL UNIQUE,
                         description VARCHAR(180) NOT NULL,
                         primary key (id)
);

CREATE TABLE tariffs(
                        id INT NOT NULL AUTO_INCREMENT,
                        name VARCHAR(45) NOT NULL UNIQUE ,
                        description VARCHAR(180) NOT NULL,
                        price INT NOT NULL,
                        service_id INT NOT NULL,
                        best INT DEFAULT 0,
                        foreign key (service_id) references services(id) ON DELETE CASCADE,
                        primary key(id)
);

INSERT INTO services(name, description) VALUES
                                            ('Internet', 'Category for Ethernet'),
                                            ('IPTV', 'tv'),
                                            ('TV', 'just tv'),
                                            ('Phone', 'call');

INSERT INTO tariffs(name, description, price, service_id) VALUES
                                                  ('EasyNet', 'Just connect and use', 50, 1),
                                                  ('FreeTV', 'Everywhere amd everyone', 100, 2),
                                                  ('IPTV', 'Watch your favorite', 123, 3),
                                                  ('Phone', 'Call everywhere', 150, 4),
                                                  ('WorldNet', 'In the each corner of the world', 200, 1);

INSERT INTO users(login, name, surname, password, admin) VALUES
                                                             ('RomanKotyk', 'Roman', 'Kotyk', 'password', true),
                                                             ('Andrii', 'Andrii', 'Marchenko', '12345678', false),
                                                             ('Fedko', 'Nazar', 'Shevchenko', '12345678', false),
                                                             ('Ooleg', 'Oleg', 'Kost', '12345678', false),
                                                             ('Max', 'Maksym', 'Rak', 'pass1234', false),
                                                             ('1', '1', '1', '1', false),
                                                             ('2', '2', 'Rak', 'pass1234', false),
                                                             ('3', '2', 'Rak', 'pass1234', false),
                                                             ('4', 'Maksym', 'Rak', 'pass1234', false),
                                                             ('5', 'Maksym', 'Rak', 'pass1234', false),
                                                             ('6', 'Maksym', 'Rak', 'pass1234', false);

CREATE TABLE subcribers(
       user_id INT NOT NULL,
       tariff_id INT NOT NULL,
       write_off DATE NOT NULL,
       primary key (user_id, tariff_id),
       foreign key (tariff_id) references tariffs(id) ON DELETE CASCADE,
       foreign key (user_id) references users(id) ON DELETE CASCADE
);
