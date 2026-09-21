CREATE TABLE product_images (
    id         BIGSERIAL    PRIMARY KEY,
    product_id BIGINT       NOT NULL,
    image_url  VARCHAR(500) NOT NULL,
    sort_order INT          NOT NULL,
    CONSTRAINT fk_product_images_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE INDEX idx_product_images_product_id ON product_images (product_id);

-- Replace the old random placeholder photos with curated, brand-neutral product shots,
-- two per product (cover + a secondary angle) so the new gallery has something to show.
INSERT INTO product_images (product_id, image_url, sort_order)
SELECT id, 'https://images.unsplash.com/photo-1741896135512-084b251887f7?auto=format&fit=crop&w=900&q=80', 0
FROM products WHERE category = 'Serums';
INSERT INTO product_images (product_id, image_url, sort_order)
SELECT id, 'https://images.unsplash.com/photo-1608571423539-e951b9b3871e?auto=format&fit=crop&w=900&q=80', 1
FROM products WHERE category = 'Serums';

INSERT INTO product_images (product_id, image_url, sort_order)
SELECT id, 'https://images.unsplash.com/photo-1608571423539-e951b9b3871e?auto=format&fit=crop&w=900&q=80', 0
FROM products WHERE category = 'Hair Care';
INSERT INTO product_images (product_id, image_url, sort_order)
SELECT id, 'https://images.unsplash.com/photo-1699885725103-2bf0a3c38cbc?auto=format&fit=crop&w=900&q=80', 1
FROM products WHERE category = 'Hair Care';

INSERT INTO product_images (product_id, image_url, sort_order)
SELECT id, 'https://images.unsplash.com/photo-1629732097571-b042b35aa3ed?auto=format&fit=crop&w=900&q=80', 0
FROM products WHERE category IN ('Moisturizer', 'Face Mask');
INSERT INTO product_images (product_id, image_url, sort_order)
SELECT id, 'https://images.unsplash.com/photo-1699885725103-2bf0a3c38cbc?auto=format&fit=crop&w=900&q=80', 1
FROM products WHERE category IN ('Moisturizer', 'Face Mask');

INSERT INTO product_images (product_id, image_url, sort_order)
SELECT id, 'https://images.unsplash.com/photo-1699885725103-2bf0a3c38cbc?auto=format&fit=crop&w=900&q=80', 0
FROM products WHERE category IN ('Sunscreen', 'Cleanser', 'Body Care', 'Female Care', 'Toner');
INSERT INTO product_images (product_id, image_url, sort_order)
SELECT id, 'https://images.unsplash.com/photo-1629732097571-b042b35aa3ed?auto=format&fit=crop&w=900&q=80', 1
FROM products WHERE category IN ('Sunscreen', 'Cleanser', 'Body Care', 'Female Care', 'Toner');

ALTER TABLE products DROP COLUMN image_url;
