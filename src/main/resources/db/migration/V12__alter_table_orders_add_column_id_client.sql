ALTER TABLE orders ADD COLUMN id_client INTEGER, ADD CONSTRAINT fk_id_client FOREIGN KEY (id_client) REFERENCES clients (id);