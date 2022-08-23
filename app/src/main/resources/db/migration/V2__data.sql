INSERT INTO account_details (id, name, surname)
VALUES (1, 'Jack', 'Sparrow'),
       (2, 'Hector', 'Barbossa');

INSERT INTO account(id, saldo, account_details_id)
VALUES (1000, 100, 1),
       (2000, 100, 2);