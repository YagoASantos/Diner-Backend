CREATE TABLE orders(
    id SERIAL PRIMARY KEY,
    description VARCHAR(100),
    deleted_at TIMESTAMP
);
CREATE TABLE orders_hamburguers(
    id_order INTEGER,
    id_hamburger INTEGER,
    PRIMARY KEY (id_order, id_hamburger),
    FOREIGN KEY (id_hamburger) REFERENCES hamburguers (id),
    FOREIGN KEY (id_order) REFERENCES orders (id)
);
CREATE TABLE orders_drinks(
    id_order INTEGER,
    id_drink INTEGER,
    PRIMARY KEY (id_order, id_drink),
    FOREIGN KEY (id_drink) REFERENCES drinks (id),
    FOREIGN KEY (id_order) REFERENCES orders (id)
);