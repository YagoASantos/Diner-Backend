CREATE TABLE hamburguers(
    id SERIAL PRIMARY KEY,
    description VARCHAR(100),
    unity_price NUMERIC(5,2),
    deleted_at TIMESTAMP
);
CREATE TABLE hamburguers_ingredients(
    id_hamburguer INTEGER,
    id_ingredient INTEGER,
    PRIMARY KEY (id_hamburguer, id_ingredient),
    FOREIGN KEY (id_hamburguer) REFERENCES hamburguers (id),
    FOREIGN KEY (id_ingredient) REFERENCES ingredients (id)
);