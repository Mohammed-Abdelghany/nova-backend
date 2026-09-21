CREATE TABLE admins (
    id         BIGSERIAL     PRIMARY KEY,
    username   VARCHAR(100)  NOT NULL,
    password   VARCHAR(255)  NOT NULL,
    created_at TIMESTAMP     NOT NULL,
    CONSTRAINT uk_admins_username UNIQUE (username)
);

-- Default admin: username "admin", password "Admin@123" (BCrypt hashed below).
-- Change this password immediately via PUT /api/v1/auth/change-password after first login.
INSERT INTO admins (username, password, created_at) VALUES
('admin', '$2a$10$OmMESDsUnz5ASU3cSRw.HOa7BL5zGF2XF6NgL06iYaAOnqJZyRf1u', NOW());
