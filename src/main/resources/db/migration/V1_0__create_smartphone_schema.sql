CREATE TABLE smartphone
(
    id           SERIAL PRIMARY KEY,
    manufacturer VARCHAR(50)  NOT NULL,
    model    VARCHAR(100) NOT NULL,
    color        VARCHAR(30),
    memory_size  INTEGER,
    price        DECIMAL(10, 2),
    UNIQUE (model, color, memory_size)
);

INSERT INTO smartphone (manufacturer, model, color, memory_size, price)
VALUES ('Apple', 'IPhone 13', 'Black', 128, 799.99),
       ('Apple', 'IPhone 13 Pro', 'Silver', 256, 999.99),
       ('Apple', 'IPhone 12', 'Blue', 64, 699.99),
       ('Apple', 'IPhone 12 Mini', 'Green', 128, 599.99),
       ('Apple', 'IPhone SE', 'Red', 64, 399.99),

       ('Xiaomi', 'Mi 11', 'Black', 128, 749.99),
       ('Xiaomi', 'Redmi Note 10', 'Blue', 64, 199.99),
       ('Xiaomi', 'Poco X3', 'Gray', 128, 299.99),
       ('Xiaomi', 'Mi 10T Pro', 'Silver', 256, 549.99),
       ('Xiaomi', 'Redmi 9', 'Purple', 32, 149.99),

       ('Samsung', 'Galaxy S21', 'White', 128, 799.99),
       ('Samsung', 'Galaxy Note 20', 'Bronze', 256, 999.99),
       ('Samsung', 'Galaxy A52', 'Blue', 128, 349.99),
       ('Samsung', 'Galaxy S20 FE', 'Red', 128, 599.99),
       ('Samsung', 'Galaxy Z Fold 3', 'Black', 256, 1799.99);
