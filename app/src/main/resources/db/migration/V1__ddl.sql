-- public.account_details definition

-- Drop table

-- DROP TABLE public.account_details;

CREATE TABLE public.account_details (
                                        id bigserial NOT NULL,
                                        name varchar(255) NULL,
                                        surname varchar(255) NULL,
                                        CONSTRAINT account_details_pkey PRIMARY KEY (id)
);


-- public.account definition

-- Drop table

-- DROP TABLE public.account;

CREATE TABLE public.account (
                                id bigserial NOT NULL,
                                saldo numeric(38, 2) NULL,
                                account_details_id int8 NOT NULL,
                                CONSTRAINT account_pkey PRIMARY KEY (id),
                                CONSTRAINT fk7p4g2vswnkoqu6ce95989u8pp FOREIGN KEY (account_details_id) REFERENCES public.account_details(id)
);


-- public.account_history definition

-- Drop table

-- DROP TABLE public.account_history;

CREATE TABLE public.account_history (
                                        id bigserial NOT NULL,
                                        operation int2 NULL,
                                        "timestamp" timestamp(6) NULL,
                                        value numeric(38, 2) NULL,
                                        account_id int8 NOT NULL,
                                        from_id int8 NULL,
                                        to_id int8 NULL,
                                        CONSTRAINT account_history_pkey PRIMARY KEY (id),
                                        CONSTRAINT fkdr4wp98xrypn2xxxhj0nhofn FOREIGN KEY (to_id) REFERENCES public.account(id),
                                        CONSTRAINT fkfn3h0y7xi1n5xragpl4dg4fi4 FOREIGN KEY (from_id) REFERENCES public.account(id),
                                        CONSTRAINT fkfomk97lcdrndxotso6yce14n2 FOREIGN KEY (account_id) REFERENCES public.account(id)
);