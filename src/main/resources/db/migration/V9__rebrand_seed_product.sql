-- The storefront brand was renamed; update the one seeded product whose title
-- referenced the old brand name. Past order line items keep their original
-- snapshot title on purpose, as a historical record of what was purchased.
UPDATE products SET title = 'Nova Sunscreen SPF50+' WHERE title = 'PharmaGlow Sunscreen SPF50+';
