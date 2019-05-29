
drop table if exists account;
create table account
(
    account_id      serial       not null
        constraint account_pkey
            primary key,
    email     varchar(255) not null
        constraint account_email_key
            unique,
    address      varchar(255),
    phone_number varchar(50),
    enabled      boolean
);

drop table if exists verification_token;
create table verification_token
(
    token_id    serial not null
        constraint verification_token_pkey
            primary key,
    token       varchar(255),
    expiry_date timestamp,
    account_id     integer not null,
    type VARCHAR(50) NOT NULL
);