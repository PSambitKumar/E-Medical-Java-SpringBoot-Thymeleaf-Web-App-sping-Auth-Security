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
    
    insert into hospital.user (id, confirmation_token, username, enabled, first_name, gender, last_name, password, authority, lastseen)
    values  (23, '9860ed28-7ceb-4c5d-97d7-80fa34c88afd', 'psambitkumar0@gmail.com', true, 'sambit', 'Male', 'kumar', 'Sambitkumar@16', 'ROLE_USER', 'Fri Aug 05 10:59:58 IST 2022'),
        (26, 'ByAdmin-Panel', 'sambit@gmail.com', true, 'Sambit Kumar', 'Male', 'Pradhan', 'default', 'ROLE_ADMIN', 'Tue Aug 23 10:41:26 IST 2022');





//Updated
CREATE TABLE `app` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `email` text NOT NULL,
  `date` text NOT NULL,
  `time` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `regtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(22),
(22);


CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `confirmation_token` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `lastseen` varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


INSERT INTO `user` (`id`, `confirmation_token`, `username`, `enabled`, `first_name`, `gender`, `last_name`, `password`, `authority`, `lastseen`) VALUES
(1, '36983cce-975b-4a92-bf73-a4f41978e01c', 'sambit@gmail.com', b'1', 'Sambit', 'MALE', 'Pradhan', 'sambitKumar', 'ROLE_ADMIN', 'Thu Aug 22 00:00:56 IST 2022');



insert into hospital.user (id, confirmation_token, username, enabled, first_name, gender, last_name, password, authority, lastseen)
values  (3, '9860ed28-7ceb-4c5d-97d7-80fa34c88afd', 'psambitkumar0@gmail.com', true, 'sambit', 'Male', 'kumar', 'Sambitkumar@16', 'ROLE_USER', 'Thu Aug 25 10:44:20 IST 2022'),
        (4, 'ByAdmin-Panel', 'sambit@gmail.com', true, 'Sambit Kumar', 'Male', 'Pradhan', 'default', 'ROLE_ADMIN', 'Tue Aug 23 10:41:26 IST 2022');

