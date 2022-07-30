CREATE TYPE currency as ENUM('PLN', 'USD', 'EUR');

CREATE TABLE products
(
    id UUID PRIMARY KEY,
    type VARCHAR(200),
    expiration_date TIMESTAMP,
    total_price numeric,
    currency currency
);