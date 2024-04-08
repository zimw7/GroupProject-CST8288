DROP DATABASE IF EXISTS FWRP;

CREATE DATABASE FWRP;

USE FWRP;

CREATE TABLE USERS
(
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	USER_NAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(50) NOT NULL,
	USER_TYPE ENUM('RETAIL', 'CUSTOMER','CHARITY') NOT NULL,
	PHONE_NUMBER VARCHAR(50) NOT NULL,
	EMAIL VARCHAR(50) NOT NULL,
	IS_SUBSCRIBED BOOLEAN NOT NULL
);

CREATE TABLE FOOD
(
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(50) NOT NULL,
	QUANTITY INT NOT NULL,
	PRICE DECIMAL(8,2) NOT NULL,
	FOOD_TYPE ENUM('DAIRY', 'PERISHABLE','CARBOHYDRATE','DRINK') NOT NULL,
	EXPIRATION_DATE DATE,
	USER_ID INT NOT NULL,
	FOREIGN KEY(USER_ID) REFERENCES USERS(ID)
);

CREATE TABLE SURPLUS_FOOD
(
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    QUANTITY INT NOT NULL,
    FOOD_TYPE ENUM('DAIRY', 'PERISHABLE', 'CARBOHYDRATE', 'DRINK') NOT NULL,
    EXPIRATION_DATE DATE,
    PRICE DECIMAL(8,2) NOT NULL,
    DISCOUNT_RATE DECIMAL(8,2) NOT NULL,
    IS_FOR_DONATION BOOLEAN NOT NULL,
    USER_ID INT NOT NULL,
    FOREIGN KEY(USER_ID) REFERENCES USERS(ID)
);

CREATE TABLE SUBSCRIPTION
(
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	USER_ID INT NOT NULL,
	CONTACT_TYPE ENUM('TEXT', 'EMAIL') NOT NULL,
	PREFERENCE_TYPE ENUM('DAIRY', 'PERISHABLE', 'CARBOHYDRATE', 'DRINK') NOT NULL,
        RETAILER_USERNAME VARCHAR(50) NOT NULL, 
	FOREIGN KEY(USER_ID) REFERENCES USERS(ID)
);

CREATE TABLE ALERT
(
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	USER_ID INT NOT NULL,
        MESSAGE VARCHAR(1024) NOT NULL, 
	FOREIGN KEY(USER_ID) REFERENCES USERS(ID)
);

INSERT INTO USERS (USER_NAME, PASSWORD, USER_TYPE, PHONE_NUMBER, EMAIL, IS_SUBSCRIBED) VALUES
('SOBEYS', 'PASSWORD','RETAIL', '613-111-1111', 'SOBEYS@GMAIL.COM', FALSE),
('Walmart', 'walmart123', 'RETAIL', '800-555-1234', 'contact@walmart.com', false),
('Costco', 'costco456', 'RETAIL', '800-555-5678', 'info@costco.com', FALSE),
('Target', 'target789', 'RETAIL', '800-555-9876', 'customer.service@target.com', FALSE),
('JOE DOE', 'PASSWORD', 'CUSTOMER', '613-222-2222', 'JOE@GMAIL.COM', TRUE),
('Alice Smith', 'alicepass', 'CUSTOMER', '555-111-2222', 'alice.smith@email.com', true),
('Bob Johnson', 'bobpass', 'CUSTOMER', '555-444-5555', 'bob.johnson@email.com', true),
('RED CROSS', 'PASSWORD', 'CHARITY', '613-333-3333', 'RED@GMAIL.COM', TRUE),
('Save The Children', 'savethechildren123', 'CHARITY', '555-123-4567', 'info@savethechildren.org', true),
('World Vision', 'worldvision123', 'CHARITY', '555-987-6543', 'contact@worldvision.org', true);

INSERT INTO FOOD (NAME, QUANTITY, PRICE, FOOD_TYPE, EXPIRATION_DATE, USER_ID) VALUES
('Milk', 10, 2.99, 'DAIRY', '2024-04-01', (SELECT ID FROM USERS WHERE USER_NAME = 'SOBEYS')),
('Cheese', 5, 4.50, 'DAIRY', '2024-04-05', (SELECT ID FROM USERS WHERE USER_NAME = 'walmart')),
('Eggs', 20, 3.99, 'PERISHABLE', '2024-03-30', (SELECT ID FROM USERS WHERE USER_NAME = 'SOBEYS')),
('Bread', 15, 2.49, 'CARBOHYDRATE', '2024-04-10', (SELECT ID FROM USERS WHERE USER_NAME = 'costco')),
('Apples', 30, 0.75, 'PERISHABLE', '2024-03-28', (SELECT ID FROM USERS WHERE USER_NAME = 'SOBEYS')),
('Chicken', 8, 7.99, 'PERISHABLE', '2024-03-31', (SELECT ID FROM USERS WHERE USER_NAME = 'target')),
('Potatoes', 25, 1.50, 'CARBOHYDRATE', '2024-04-02', (SELECT ID FROM USERS WHERE USER_NAME = 'costco')),
('Orange Juice', 12, 3.25, 'DRINK', '2024-04-08', (SELECT ID FROM USERS WHERE USER_NAME = 'target')),
('Pasta', 10, 1.99, 'CARBOHYDRATE', '2024-04-15', (SELECT ID FROM USERS WHERE USER_NAME = 'walmart')),
('Yogurt', 6, 2.25, 'DAIRY', '2024-04-03', (SELECT ID FROM USERS WHERE USER_NAME = 'target')),
('Rice', 20, 4.75, 'CARBOHYDRATE', '2024-04-20', (SELECT ID FROM USERS WHERE USER_NAME = 'walmart'));

INSERT INTO SURPLUS_FOOD (NAME, QUANTITY, FOOD_TYPE, EXPIRATION_DATE, PRICE, IS_FOR_DONATION, DISCOUNT_RATE, USER_ID) VALUES
('Yogurt', 4, 'DAIRY', '2024-04-05', 1.75, TRUE, 0.15, (SELECT ID FROM USERS WHERE USER_NAME = 'SOBEYS')),
('Apples', 10, 'PERISHABLE', '2024-04-02', 0.50, TRUE, 0.20, (SELECT ID FROM USERS WHERE USER_NAME = 'SOBEYS')),
('Chicken', 5, 'PERISHABLE', '2024-03-30', 6.99, TRUE, 0.20, (SELECT ID FROM USERS WHERE USER_NAME = 'target')),
('Bread', 3, 'CARBOHYDRATE', '2024-04-08', 1.99, TRUE, 0.25, (SELECT ID FROM USERS WHERE USER_NAME = 'costco')),
('Rice', 8, 'CARBOHYDRATE', '2024-04-10', 4.50, TRUE, 0.15, (SELECT ID FROM USERS WHERE USER_NAME = 'walmart')),
('Cheese', 3, 'DAIRY', '2024-04-15', 3.75, TRUE, 0.20, (SELECT ID FROM USERS WHERE USER_NAME = 'walmart')),
('Oranges', 7, 'PERISHABLE', '2024-03-25', 0.60, TRUE, 0.15, (SELECT ID FROM USERS WHERE USER_NAME = 'costco')),
('Beef', 6, 'PERISHABLE', '2024-03-28', 9.99, TRUE, 0.20, (SELECT ID FROM USERS WHERE USER_NAME = 'target')),
('Cereal', 4, 'CARBOHYDRATE', '2024-04-05', 2.49, TRUE, 0.25, (SELECT ID FROM USERS WHERE USER_NAME = 'walmart')),
('Apple Juice', 10, 'DRINK', '2024-04-12', 2.75, TRUE, 0.20, (SELECT ID FROM USERS WHERE USER_NAME = 'target')),
('Peanut Butter', 5, 'CARBOHYDRATE', '2024-04-08', 3.99, TRUE, 0.15, (SELECT ID FROM USERS WHERE USER_NAME = 'costco')),
('Tomatoes', 8, 'PERISHABLE', '2024-03-29', 1.25, TRUE, 0.20, (SELECT ID FROM USERS WHERE USER_NAME = 'walmart'));

INSERT INTO SUBSCRIPTION (USER_ID, CONTACT_TYPE, PREFERENCE_TYPE, RETAILER_USERNAME) VALUES
((SELECT ID FROM USERS WHERE USER_NAME = 'JOE DOE'), 'TEXT', 'DAIRY', 'SOBEYS'),
((SELECT ID FROM USERS WHERE USER_NAME = 'Alice Smith'), 'EMAIL', 'PERISHABLE', 'Walmart'),
((SELECT ID FROM USERS WHERE USER_NAME = 'Bob Johnson'), 'TEXT', 'CARBOHYDRATE', 'Costco'),
((SELECT ID FROM USERS WHERE USER_NAME = 'RED CROSS'), 'EMAIL', 'DAIRY', 'Target'),
((SELECT ID FROM USERS WHERE USER_NAME = 'Save The Children'), 'TEXT', 'PERISHABLE', 'Walmart'),
((SELECT ID FROM USERS WHERE USER_NAME = 'World Vision'), 'EMAIL', 'DRINK', 'Costco');

select * from USERS;
select * from SUBSCRIPTION;
select * from FOOD;
select * from SURPLUS_FOOD;
select * from ALERT;