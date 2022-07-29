CREATE TYPE currency as ENUM('PLN', 'USD', 'EUR');

CREATE TABLE products
(
    id UUID PRIMARY KEY,
    type VARCHAR(200),
    expiration_date TIMESTAMP,
    total_price numeric,
    currency currency
);

INSERT INTO products (id, type, expiration_date, total_price, currency) values (gen_random_uuid(), 'MILK', NOW() + INTERVAL '1 hour', 4.11, 'PLN');
INSERT INTO products (id, type, expiration_date, total_price, currency) values (gen_random_uuid(), 'LAPTOP', NOW() + INTERVAL '26 hour', 1224.11, 'PLN');