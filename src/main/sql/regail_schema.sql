use reagail;

CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email_address VARCHAR(255) NOT NULL UNIQUE,
    is_admin BOOL DEFAULT FALSE
);

CREATE TABLE passwords(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
password VARCHAR(255) NOT NULL,
user_id INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES users (id)
);


CREATE TABLE items(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(60) NOT NULL,
description VARCHAR(255) NOT NULL,
color VARCHAR(255) NOT NULL,
category VARCHAR(60) NOT NULL,
brand VARCHAR(60) NOT NULL,
quantity INT NOT NULL,
price FLOAT NOT NULL
);

CREATE TABLE billing_addresses(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
street VARCHAR(100) NOT NULL,
province VARCHAR(20) NOT NULL,
country VARCHAR(20) NOT NULL,
postal_code VARCHAR(6) NOT NULL
);

CREATE TABLE payments(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
payment_type VARCHAR(255) NOT NULL,
credit_card_name VARCHAR(255) NOT NULL,
credit_card_number VARCHAR(255) NOT NULL,
credit_card_expiration DATE NOT NULL,
credit_card_cvv VARCHAR(3)
);

CREATE TABLE orders(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
total FLOAT NOT NULL,
user_id INT NOT NULL,
billing_address_id INT NOT NULL,
payment_id INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES users (id),
FOREIGN KEY (billing_address_id) REFERENCES billing_addresses (id),
FOREIGN KEY (payment_id) REFERENCES payments (id)
);

CREATE TABLE order_items(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
order_id INT NOT NULL,
item_id INT NOT NULL,
amount INT NOT NULL,
FOREIGN KEY (item_id) REFERENCES items (id),
FOREIGN KEY (order_id) REFERENCES orders (id)
);

CREATE TABLE reviews(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
rating INT NOT NULL,
title VARCHAR(255) NOT NULL,
description VARCHAR(255) NOT NULL,
date DATE NOT NULL,
item_id INT NOT NULL,
user_id INT NOT NULL,
FOREIGN KEY (item_id) REFERENCES items (id),
FOREIGN KEY (user_id) REFERENCES users (id)
);