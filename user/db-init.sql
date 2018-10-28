USE test;

CREATE TABLE Users(
    username VARCHAR(16) NOT NULL PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    birthday DATE UNIQUE NOT NULL,
    lang VARCHAR(2) NOT NULL
);

INSERT INTO Users(username, first_name, last_name, birthday, lang) VALUES ('sozal', 'Serkan', 'OZAL', '1986-09-15', 'TR');