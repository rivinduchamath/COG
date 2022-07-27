


create table hibernate_sequence
(
    next_val bigint null
);

create table oauth_access_token
(
    token_id          varchar(255) not null
        primary key,
    authentication    mediumblob   null,
    authentication_id varchar(255) null,
    client_id         varchar(255) null,
    refresh_token     varchar(255) null,
    token             mediumblob   null,
    user_name         varchar(255) null
);

create table oauth_approvals
(
    userId         varchar(255) not null
        primary key,
    clientId       varchar(255) null,
    expiresAt      datetime(6)  null,
    lastModifiedAt datetime(6)  null,
    scope          varchar(255) null
);

create table oauth_client_details
(
    client_id               varchar(255) not null
        primary key,
    autoapprove             varchar(255) null,
    access_token_validity   int          null,
    additional_information  varchar(255) null,
    authorities             varchar(255) null,
    authorized_grant_types  varchar(255) null,
    client_secret           varchar(255) null,
    refresh_token_validity  int          null,
    resource_ids            varchar(255) null,
    scope                   varchar(255) null,
    web_server_redirect_uri varchar(255) null
);

create table oauth_client_token
(
    token_id          varchar(255) not null
        primary key,
    authentication_id varchar(255) null,
    client_id         varchar(255) null,
    token             mediumblob   null,
    user_name         varchar(255) null
);

create table oauth_code
(
    code           varchar(256) null,
    authentication mediumblob   null
);

create table oauth_refresh_token
(
    token_id       varchar(256) null,
    token          mediumblob   null,
    authentication mediumblob   null
);

create table permission
(
    id   int          not null
        primary key,
    name varchar(255) null
);

create table auth_role
(
    id   int          not null
        primary key,
    name varchar(255) null,
    constraint UK_8sewwnpamngi6b1dwaa88askk
        unique (name)
);

create table permission_role
(
    role_id       int not null,
    permission_id int not null,
    constraint FK3tuvkbyi6wcytyg21hvpd6txw
        foreign key (permission_id) references permission (id),
    constraint FK50sfdcvbvdaclpn7wp4uop4ml
        foreign key (role_id) references auth_role (id)
);

create table auth_user
(
    id                    bigint        not null
        primary key,
    accountNonExpired     bit           null,
    accountNonLocked      bit           null,
    credentialsNonExpired bit           null,
    enabled               bit           null,
    password              varchar(1000) null,
    email                 varchar(255)  null,
    constraint UK_ob8kqyqqgmefl0aco34akdtpe
        unique (email)
);

create table role_user
(
    user_id bigint not null,
    role_id int    not null,
    constraint FK4320p8bgvumlxjkohtbj214qi
        foreign key (user_id) references auth_user (id),
    constraint FKiqpmjd2qb4rdkej916ymonic6
        foreign key (role_id) references auth_role (id)
);


DROP TABLE if exists oauth_refresh_token;
create table if not exists oauth_refresh_token (
                                                   token_id VARCHAR(256),
                                                   token LONG VARBINARY,
                                                   authentication LONG VARBINARY
);

DROP TABLE oauth_code;
create table if not exists oauth_code (
             code VARCHAR(256), authentication LONG VARBINARY
);




INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES ('mobile', '{bcrypt}$2a$12$v7BWLKlyF5H1k8NBYBvBI.C5SBziVSRZDpmW/6Es9kGpxe3l.dbKu', 'http://localhost:8080/login', 'READ,WRITE', '3600', '10000', 'inventory,payment', 'authorization_code,password,refresh_token,implicit', '{}');

INSERT INTO permission (id, NAME) VALUES
                                      (1,'create_profile'),
                                      (2,'read_profile'),
                                      (3,'update_profile'),
                                      (4,'delete_profile');

INSERT INTO auth_role (id, NAME) VALUES
                                (1,'ROLE_admin'),(2,'ROLE_operator'),(3,'ROLE_editor');

INSERT INTO permission_role (PERMISSION_ID, ROLE_ID) VALUES
                                                         (1,1),
                                                         (2,1),
                                                         (3,1),
                                                         (4,1),
                                                         (1,3),
                                                         (4,2),
                                                         (3,2);
insert into auth_user (id, email,password,  enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('1', 'chamath','{bcrypt}$2a$12$v7BWLKlyF5H1k8NBYBvBI.C5SBziVSRZDpmW/6Es9kGpxe3l.dbKu',  true,true,true,true);
insert into  auth_user (id, email,password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('2', 'suranga', '{bcrypt}$2a$12$v7BWLKlyF5H1k8NBYBvBI.C5SBziVSRZDpmW/6Es9kGpxe3l.dbKu',   true,true,true,true);
insert into  auth_user (id, email,password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('3', 'rivindu', '{bcrypt}$2a$12$v7BWLKlyF5H1k8NBYBvBI.C5SBziVSRZDpmW/6Es9kGpxe3l.dbKu',   true,true,true,true);

INSERT INTO role_user (ROLE_ID, USER_ID)
VALUES
    (1, 1) ,
    (2, 1) ,
    (2, 2) ;

UPDATE hibernate_sequence
SET next_val = 7
where next_val = 1;