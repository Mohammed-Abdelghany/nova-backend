ALTER TABLE products
    ADD COLUMN available BOOLEAN NOT NULL DEFAULT TRUE,
    ADD COLUMN discount_percentage DECIMAL(5, 2) NOT NULL DEFAULT 0;

-- Sample discounts so the "sale" badge has something to show out of the box.
UPDATE products SET discount_percentage = 15.00 WHERE title = 'PharmaGlow Sunscreen SPF50+';
UPDATE products SET discount_percentage = 20.00 WHERE title = 'Vitamin C Brightening Serum';
UPDATE products SET discount_percentage = 10.00 WHERE title = 'Argan Hair Repair Oil';
UPDATE products SET discount_percentage = 30.00 WHERE title = 'Clay Purifying Face Mask';
UPDATE products SET discount_percentage = 25.00 WHERE title = 'Intimate Wash pH Balanced';

-- A couple of out-of-stock samples so the "unavailable" state is visible too.
UPDATE products SET available = FALSE WHERE title = 'Retinol Night Serum';
UPDATE products SET available = FALSE WHERE title = 'Keratin Repair Hair Mask';
