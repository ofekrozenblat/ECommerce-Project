INSERT INTO billing_addresses (street, province, country, postal_code)
VALUES ('123 Main St', 'Ontario', 'Canada', 'M4S2M4'),
       ('456 Elm St', 'Quebec', 'Canada', 'H4N1W5'),
       ('789 Oak St', 'Alberta', 'Canada', 'T6E2G2');
       
INSERT INTO payments (payment_type, credit_card_name, credit_card_number, credit_card_expiration, credit_card_cvv)
VALUES (1, 'John Smith', '1234567812345678', '01/24', '123'),
       (2, 'Jane Doe', '2345678923456789', '11/23', '456'),
       (3, 'Bob Johnson', '3456789034567890', '03/25', '789');