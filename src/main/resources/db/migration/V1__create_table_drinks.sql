CREATE TABLE drinks(
    id SERIAL PRIMARY KEY,
    description VARCHAR(100),
    unity_price NUMERIC(5,2),
    have_sugar BOOLEAN
);