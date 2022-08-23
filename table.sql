create or replace table app
(
    id          int auto_increment                    primary key,
    name        text                                  not null,
    email       text                                  not null,
    date        text                                  not null,
    time        varchar(100)                          not null,
    description text                                  not null,
    regtime     timestamp default current_timestamp() not null on update current_timestamp()
)
    charset = latin1;

create or replace table hibernate_sequence
(
    next_val bigint null
)
    engine = MyISAM
    charset = latin1;

create or replace table user
(
    id                 int          not null,
    confirmation_token varchar(255) null,
    username           varchar(255) not null,
    enabled            bit          null,
    first_name         varchar(255) null,
    gender             varchar(255) null,
    last_name          varchar(255) null,
    password           varchar(255) null,
    authority          varchar(255) null,
    lastseen           varchar(200) null
)
    engine = MyISAM
    charset = latin1;

