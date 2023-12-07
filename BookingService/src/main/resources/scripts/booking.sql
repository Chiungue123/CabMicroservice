CREATE TABLE BOOKING (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pickup_location VARCHAR(255),
    dropoff_location VARCHAR(255),
    pickup_time DATETIME,
    fare DECIMAL(10, 2),
    vehicle_type VARCHAR(50)
);