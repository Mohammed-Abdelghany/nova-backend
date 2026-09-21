CREATE TABLE coupons (
    id            BIGSERIAL      PRIMARY KEY,
    code          VARCHAR(50)    NOT NULL,
    discount_type VARCHAR(20)    NOT NULL,
    value         DECIMAL(10, 2) NOT NULL,
    active        BOOLEAN        NOT NULL DEFAULT TRUE,
    expiry_date   TIMESTAMP,
    created_at    TIMESTAMP      NOT NULL,
    CONSTRAINT uk_coupons_code UNIQUE (code)
);

ALTER TABLE orders
    ADD COLUMN product_price  DECIMAL(10, 2) NOT NULL DEFAULT 0,
    ADD COLUMN coupon_code    VARCHAR(50),
    ADD COLUMN discount_amount DECIMAL(10, 2) NOT NULL DEFAULT 0,
    ADD COLUMN total_price    DECIMAL(10, 2) NOT NULL DEFAULT 0;

-- Backfill pricing for orders seeded before this migration, using each order's referenced product price
UPDATE orders o
SET product_price = p.price,
    total_price = p.price
FROM products p
WHERE p.id = o.product_id;

ALTER TABLE orders
    ALTER COLUMN product_price DROP DEFAULT,
    ALTER COLUMN total_price DROP DEFAULT;

INSERT INTO coupons (code, discount_type, value, active, expiry_date, created_at) VALUES
('WELCOME10', 'PERCENTAGE', 10.00, TRUE, NULL, NOW()),
('SAVE50', 'FIXED', 50.00, TRUE, NOW() + INTERVAL '30 days', NOW());
