--
-- DATABASE CREATION
--

CREATE DATABASE IF NOT EXISTS customer_service_db;
CREATE DATABASE IF NOT EXISTS vehicle_service_db;
CREATE DATABASE IF NOT EXISTS cleaner_service_db;
CREATE DATABASE IF NOT EXISTS booking_service_db;

--
-- CUSTOMER SERVICE
--
USE customer_service_db;

CREATE TABLE IF NOT EXISTS customers (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(50),
  address VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO customers(full_name, email, phone, address)
VALUES 
 ('Alice Customer', 'alice@example.com', '123456789', 'City 1'),
 ('Bob Customer', 'bob@example.com', '987654321', 'City 2');

--
-- VEHICLE SERVICE
--
USE vehicle_service_db;

CREATE TABLE IF NOT EXISTS vehicles (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  license_plate VARCHAR(50) NOT NULL,
  active BOOLEAN DEFAULT TRUE
);

INSERT INTO vehicles(name, license_plate, active)
VALUES 
 ('Van 1', 'ABC-123', TRUE),
 ('Van 2', 'XYZ-789', TRUE);

--
-- CLEANER SERVICE
--
USE cleaner_service_db;

CREATE TABLE IF NOT EXISTS cleaners (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  vehicle_id BIGINT NOT NULL,
  active BOOLEAN DEFAULT TRUE
);

INSERT INTO cleaners(full_name, vehicle_id, active)
VALUES
 ('Cleaner One', 1, TRUE),
 ('Cleaner Two', 1, TRUE),
 ('Cleaner Three', 2, TRUE);

--
-- BOOKING SERVICE
--
USE booking_service_db;

CREATE TABLE IF NOT EXISTS bookings (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  customer_id BIGINT NOT NULL,
  vehicle_id BIGINT NOT NULL,
  start_time DATETIME NOT NULL,
  end_time DATETIME NOT NULL,
  duration_hours INT NOT NULL,
  cleaners_count INT NOT NULL,
  status VARCHAR(50) DEFAULT 'CREATED',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS booking_cleaners (
  booking_id BIGINT NOT NULL,
  cleaner_id BIGINT NOT NULL
);
