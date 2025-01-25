CREATE TABLE ingredients(
    id SERIAL PRIMARY KEY,
    description VARCHAR(100),
    unity_price NUMERIC(5,2),
    is_additional BOOLEAN
);