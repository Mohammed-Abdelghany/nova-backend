CREATE TABLE products (
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(150)   NOT NULL,
    description VARCHAR(2000),
    price       DECIMAL(10, 2) NOT NULL,
    image_url   VARCHAR(500)   NOT NULL,
    category    VARCHAR(100)   NOT NULL,
    created_at  TIMESTAMP      NOT NULL
);

CREATE TABLE orders (
    id             BIGSERIAL    PRIMARY KEY,
    customer_name  VARCHAR(150) NOT NULL,
    customer_phone VARCHAR(20)  NOT NULL,
    governorate    VARCHAR(100) NOT NULL,
    address        VARCHAR(500) NOT NULL,
    product_id     BIGINT       NOT NULL,
    product_title  VARCHAR(150) NOT NULL,
    status         VARCHAR(20)  NOT NULL,
    created_at     TIMESTAMP    NOT NULL
);

CREATE INDEX idx_orders_product_id ON orders (product_id);
CREATE INDEX idx_orders_status ON orders (status);
CREATE INDEX idx_products_category ON products (category);
