CREATE TABLE orders
(
    id UUID PRIMARY KEY,
    currency currency,
    total_price numeric,
    total_discount numeric
);

CREATE TABLE orders_description (
  order_id UUID PRIMARY KEY,
  items json
);