CREATE TABLE quantity_measurement_entity (

id INT AUTO_INCREMENT PRIMARY KEY,
operation VARCHAR(50),
this_quantity VARCHAR(100),
that_quantity VARCHAR(100),
result VARCHAR(100),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);