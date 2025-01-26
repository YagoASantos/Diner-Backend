CREATE TABLE addresses(
    id SERIAL PRIMARY KEY,
    cep VARCHAR(20),
    street VARCHAR(50),
    number INTEGER,
    complement VARCHAR(50)
);

CREATE TABLE clients(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    id_address INTEGER,
    phone VARCHAR(20),
    deleted_at TIMESTAMP,
    FOREIGN KEY(id_address) REFERENCES addresses(id)
)
