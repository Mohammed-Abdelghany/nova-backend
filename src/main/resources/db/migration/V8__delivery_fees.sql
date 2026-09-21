CREATE TABLE delivery_fees (
    id          BIGSERIAL      PRIMARY KEY,
    governorate VARCHAR(100)   NOT NULL,
    fee         DECIMAL(10, 2) NOT NULL,
    CONSTRAINT uk_delivery_fees_governorate UNIQUE (governorate)
);

INSERT INTO delivery_fees (governorate, fee) VALUES
('القاهرة', 50.00),
('الجيزة', 50.00),
('القليوبية', 50.00),
('الإسكندرية', 60.00),
('الدقهلية', 60.00),
('الغربية', 60.00),
('المنوفية', 60.00),
('الشرقية', 60.00),
('الإسماعيلية', 60.00),
('السويس', 60.00),
('بورسعيد', 60.00),
('دمياط', 60.00),
('البحيرة', 60.00),
('الفيوم', 60.00),
('بني سويف', 60.00),
('المنيا', 70.00),
('أسيوط', 70.00),
('سوهاج', 70.00),
('قنا', 70.00),
('الأقصر', 70.00),
('أسوان', 70.00),
('كفر الشيخ', 70.00),
('البحر الأحمر', 90.00),
('الوادي الجديد', 90.00),
('مطروح', 90.00),
('شمال سيناء', 90.00),
('جنوب سيناء', 90.00);

ALTER TABLE orders
    ADD COLUMN delivery_fee DECIMAL(10, 2) NOT NULL DEFAULT 0;
