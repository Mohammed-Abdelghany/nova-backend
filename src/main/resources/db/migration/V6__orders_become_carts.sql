CREATE TABLE order_items (
    id            BIGSERIAL      PRIMARY KEY,
    order_id      BIGINT         NOT NULL,
    product_id    BIGINT         NOT NULL,
    product_title VARCHAR(150)   NOT NULL,
    unit_price    DECIMAL(10, 2) NOT NULL,
    quantity      INT            NOT NULL,
    line_total    DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_order_items_order FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);

CREATE INDEX idx_order_items_order_id ON order_items (order_id);
CREATE INDEX idx_order_items_product_id ON order_items (product_id);

-- Preserve existing single-product orders as a one-line cart each.
INSERT INTO order_items (order_id, product_id, product_title, unit_price, quantity, line_total)
SELECT id, product_id, product_title, product_price, 1, product_price FROM orders;

ALTER TABLE orders
    ADD COLUMN subtotal DECIMAL(10, 2) NOT NULL DEFAULT 0;

UPDATE orders SET subtotal = product_price;

ALTER TABLE orders
    DROP COLUMN product_id,
    DROP COLUMN product_title,
    DROP COLUMN product_price,
    ALTER COLUMN subtotal DROP DEFAULT;
