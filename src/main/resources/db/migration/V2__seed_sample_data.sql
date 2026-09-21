-- Sample products (skincare & female care)
INSERT INTO products (title, description, price, image_url, category, created_at) VALUES
('PharmaGlow Sunscreen SPF50+', 'واقي شمس بفلتر عالي مناسب لكل أنواع البشرة، يستخدم كل صباح قبل التعرض للشمس بـ15 دقيقة.', 385.00, 'https://picsum.photos/seed/sunscreen1/600/600', 'Sunscreen', NOW() - INTERVAL '60 days'),
('Matte Sunscreen SPF30', 'مناسب للبشرة الدهنية والمختلطة، ملمس غير لامع، يقلل من إفراز الزيوت.', 320.00, 'https://picsum.photos/seed/sunscreen2/600/600', 'Sunscreen', NOW() - INTERVAL '58 days'),
('Tinted Sunscreen SPF50', 'واقي شمس ملون بلمسة كريم أساس خفيفة، يوحد لون البشرة مع الحماية.', 420.00, 'https://picsum.photos/seed/sunscreen3/600/600', 'Sunscreen', NOW() - INTERVAL '55 days'),

('Vitamin C Brightening Serum', 'سيروم فيتامين سي لتفتيح البشرة وتوحيد اللون، يستخدم صباحاً بعد الغسول.', 450.00, 'https://picsum.photos/seed/serum1/600/600', 'Serums', NOW() - INTERVAL '50 days'),
('Hyaluronic Acid Serum', 'سيروم حمض الهيالورونيك لترطيب عميق، مناسب لكل أنواع البشرة بما فيها الحساسة.', 395.00, 'https://picsum.photos/seed/serum2/600/600', 'Serums', NOW() - INTERVAL '48 days'),
('Niacinamide 10% Serum', 'يقلل من ظهور المسام الواسعة والبقع الداكنة، يستخدم مساءً.', 340.00, 'https://picsum.photos/seed/serum3/600/600', 'Serums', NOW() - INTERVAL '45 days'),
('Retinol Night Serum', 'سيروم ريتينول لمقاومة علامات التقدم في السن، للاستخدام الليلي فقط.', 480.00, 'https://picsum.photos/seed/serum4/600/600', 'Serums', NOW() - INTERVAL '40 days'),

('Gentle Foaming Cleanser', 'غسول رغوي لطيف يناسب البشرة الحساسة، خالي من الصابون.', 210.00, 'https://picsum.photos/seed/cleanser1/600/600', 'Cleanser', NOW() - INTERVAL '38 days'),
('Salicylic Acid Cleanser', 'غسول لعلاج حب الشباب وتنظيف المسام العميق، مناسب للبشرة الدهنية.', 250.00, 'https://picsum.photos/seed/cleanser2/600/600', 'Cleanser', NOW() - INTERVAL '35 days'),
('Micellar Water', 'ماء ميسيلار لإزالة المكياج وتنظيف البشرة بدون شطف، مناسب لكل أنواع البشرة.', 180.00, 'https://picsum.photos/seed/cleanser3/600/600', 'Cleanser', NOW() - INTERVAL '33 days'),

('Ceramide Moisturizing Cream', 'كريم مرطب غني بالسيراميد لتقوية حاجز البشرة، يستخدم صباحاً ومساءً.', 360.00, 'https://picsum.photos/seed/moisturizer1/600/600', 'Moisturizer', NOW() - INTERVAL '30 days'),
('Oil-Free Gel Moisturizer', 'مرطب هلامي خفيف خالي من الزيوت، مثالي للبشرة الدهنية والمختلطة.', 280.00, 'https://picsum.photos/seed/moisturizer2/600/600', 'Moisturizer', NOW() - INTERVAL '28 days'),

('Argan Hair Repair Oil', 'زيت الأرجان لإصلاح تلف الشعر وإضافة لمعان طبيعي، يستخدم على الشعر الرطب أو الجاف.', 275.00, 'https://picsum.photos/seed/hair1/600/600', 'Hair Care', NOW() - INTERVAL '25 days'),
('Anti-Hairfall Shampoo', 'شامبو لتقليل تساقط الشعر وتقوية الجذور، يستخدم 3 مرات أسبوعياً.', 230.00, 'https://picsum.photos/seed/hair2/600/600', 'Hair Care', NOW() - INTERVAL '22 days'),
('Keratin Repair Hair Mask', 'ماسك كيراتين مكثف لإصلاح الشعر التالف، يستخدم مرة أسبوعياً.', 310.00, 'https://picsum.photos/seed/hair3/600/600', 'Hair Care', NOW() - INTERVAL '20 days'),

('Intimate Wash pH Balanced', 'غسول للعناية الحميمة بمعدل حموضة متوازن، لطيف ومناسب للاستخدام اليومي.', 195.00, 'https://picsum.photos/seed/femcare1/600/600', 'Female Care', NOW() - INTERVAL '18 days'),
('Feminine Soothing Cream', 'كريم مهدئ للمنطقة الحساسة، خالي من العطور والكحول.', 240.00, 'https://picsum.photos/seed/femcare2/600/600', 'Female Care', NOW() - INTERVAL '15 days'),

('Rose Water Toner', 'تونر ماء الورد الطبيعي لتهدئة وتنعيم البشرة بعد الغسول.', 165.00, 'https://picsum.photos/seed/toner1/600/600', 'Toner', NOW() - INTERVAL '12 days'),
('Clay Purifying Face Mask', 'ماسك طيني لتنقية البشرة وتقليل الدهون الزائدة، يستخدم مرتين أسبوعياً.', 220.00, 'https://picsum.photos/seed/mask1/600/600', 'Face Mask', NOW() - INTERVAL '10 days'),
('Body Firming Lotion', 'لوشن لشد ترطيب الجسم، غني بالكولاجين وفيتامين E.', 300.00, 'https://picsum.photos/seed/body1/600/600', 'Body Care', NOW() - INTERVAL '7 days');

-- Sample orders referencing the products above (by their generated ids in insertion order)
INSERT INTO orders (customer_name, customer_phone, governorate, address, product_id, product_title, status, created_at) VALUES
('منى عبد الرحمن', '01012345678', 'القاهرة', 'مدينة نصر، شارع مكرم عبيد، عمارة 12', 1, 'PharmaGlow Sunscreen SPF50+', 'COMPLETED', NOW() - INTERVAL '20 days'),
('سارة محمد علي', '01123456789', 'الجيزة', 'الدقي، شارع التحرير، برج النيل', 4, 'Vitamin C Brightening Serum', 'COMPLETED', NOW() - INTERVAL '18 days'),
('ياسمين خالد', '01234567890', 'الإسكندرية', 'سموحة، شارع فوزي معاذ', 8, 'Gentle Foaming Cleanser', 'COMPLETED', NOW() - INTERVAL '15 days'),
('نور الهدى إبراهيم', '01098765432', 'المنصورة', 'حي الجامعة، شارع الجمهورية', 13, 'Argan Hair Repair Oil', 'PENDING', NOW() - INTERVAL '10 days'),
('هبة الله سمير', '01187654321', 'القاهرة', 'المعادي، شارع 9، فيلا 5', 5, 'Hyaluronic Acid Serum', 'PENDING', NOW() - INTERVAL '8 days'),
('مريم عادل', '01276543210', 'الجيزة', '6 أكتوبر، الحي المتميز', 16, 'Intimate Wash pH Balanced', 'PENDING', NOW() - INTERVAL '6 days'),
('داليا حسن', '01055566677', 'القليوبية', 'بنها، شارع سعد زغلول', 11, 'Ceramide Moisturizing Cream', 'COMPLETED', NOW() - INTERVAL '4 days'),
('رنا وليد', '01144433322', 'الشرقية', 'الزقازيق، شارع الجلاء', 19, 'Clay Purifying Face Mask', 'PENDING', NOW() - INTERVAL '2 days'),
('إيمان فتحي', '01233322211', 'الغربية', 'طنطا، شارع البحر', 7, 'Retinol Night Serum', 'PENDING', NOW() - INTERVAL '1 day'),
('علياء ممدوح', '01011122233', 'القاهرة', 'مصر الجديدة، شارع الحجاز', 20, 'Body Firming Lotion', 'PENDING', NOW());
