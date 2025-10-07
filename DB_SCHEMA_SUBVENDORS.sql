-- Run this on your MySQL database to create the `subvendors` table used by the app
-- Adjust the engine and charset as needed for your MySQL server

CREATE TABLE IF NOT EXISTS subvendors (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  businessname VARCHAR(100) NOT NULL UNIQUE,
  contact_person VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  phone VARCHAR(11) NOT NULL UNIQUE,
  address TEXT NOT NULL,
  password_hash TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
