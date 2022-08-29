CREATE SCHEMA demo;

-- demo.account_owner definition

-- Drop table

-- DROP TABLE demo.account_owner;

CREATE TABLE demo.account_owner (
                                    id bigserial NOT NULL,
                                    name varchar(255) NULL,
                                    surname varchar(255) NULL,
                                    CONSTRAINT account_owner_pkey PRIMARY KEY (id)
);


-- demo.account_state_cdc definition

-- Drop table

-- DROP TABLE demo.account_state_cdc;

CREATE TABLE demo.account_state_cdc (
                                        id bigserial NOT NULL,
                                        account_id int8 NOT NULL,
                                        account_owner_id int8 NOT NULL,
                                        balance numeric(38, 2) NULL,
                                        name varchar(255) NOT NULL,
                                        CONSTRAINT account_state_cdc_pkey PRIMARY KEY (id),
                                        CONSTRAINT accountid UNIQUE (account_id)
);


-- demo.account definition

-- Drop table

-- DROP TABLE demo.account;

CREATE TABLE demo.account (
                              id bigserial NOT NULL,
                              balance numeric(38, 2) NULL,
                              account_owner_id int8 NOT NULL,
                              CONSTRAINT account_pkey PRIMARY KEY (id),
                              CONSTRAINT fk6dajikso2y9eqsr8ww9on1wpv FOREIGN KEY (account_owner_id) REFERENCES demo.account_owner(id)
);


-- demo.account_history definition

-- Drop table

-- DROP TABLE demo.account_history;

CREATE TABLE demo.account_history (
                                      id bigserial NOT NULL,
                                      operation int2 NULL,
                                      "timestamp" timestamp(6) NULL,
                                      value numeric(38, 2) NULL,
                                      account_id int8 NOT NULL,
                                      from_id int8 NULL,
                                      to_id int8 NULL,
                                      CONSTRAINT account_history_pkey PRIMARY KEY (id),
                                      CONSTRAINT fkdr4wp98xrypn2xxxhj0nhofn FOREIGN KEY (to_id) REFERENCES demo.account(id),
                                      CONSTRAINT fkfn3h0y7xi1n5xragpl4dg4fi4 FOREIGN KEY (from_id) REFERENCES demo.account(id),
                                      CONSTRAINT fkfomk97lcdrndxotso6yce14n2 FOREIGN KEY (account_id) REFERENCES demo.account(id)
);