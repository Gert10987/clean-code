INSERT INTO scanner_app.products (id, type, expiration_date, total_price, currency) values (gen_random_uuid(), 'MILK', NOW() + INTERVAL '1 hour', 4.11, 'PLN');
INSERT INTO scanner_app.products (id, type, expiration_date, total_price, currency) values (gen_random_uuid(), 'LAPTOP', NOW() + INTERVAL '26 hour', 1224.11, 'PLN');