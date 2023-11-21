CREATE TABLE Person (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

CREATE TABLE Phonebook (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE PersonPhonebook (
    id SERIAL PRIMARY KEY,
    person_id INT REFERENCES Person(id),
    phonebook_id INT REFERENCES Phonebook(id)
);

CREATE TABLE Phonenumber (
    id SERIAL PRIMARY KEY,
    phone_type VARCHAR(255),
    phone_number VARCHAR(255),
    person_id INT REFERENCES Person(id)
);