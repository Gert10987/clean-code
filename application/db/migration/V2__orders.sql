CREATE TYPE currency as ENUM('PLN', 'USD', 'EUR');
CREATE TABLE orders
(
    id UUID PRIMARY KEY,
    currency currency,
    total_price numeric(4,2),
    total_discount numeric(4,2)
);

CREATE TABLE orders_items (
  orders_id UUID,
  products_id UUID,
  PRIMARY KEY (orders_id, products_id),
  CONSTRAINT fk_orders FOREIGN KEY(orders_id) REFERENCES orders(id),
  CONSTRAINT fk_products_id FOREIGN KEY(products_id) REFERENCES products(id)
);