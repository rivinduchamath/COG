# DROP TABLE oauth_access_token;
# create table if not exists oauth_access_token (
#                                                   token_id VARCHAR(256),
#                                                   token LONG VARBINARY,
#                                                   authentication_id VARCHAR(256) PRIMARY KEY,
#                                                   user_name VARCHAR(256),
#                                                   client_id VARCHAR(256),
#                                                   authentication LONG VARBINARY,
#                                                   refresh_token VARCHAR(256)
# );
# DROP TABLE oauth_client_token;
# create table if not exists oauth_client_token (
#                                                   token_id VARCHAR(256),
#                                                   token LONG VARBINARY,
#                                                   authentication_id VARCHAR(256) PRIMARY KEY,
#                                                   user_name VARCHAR(256),
#                                                   client_id VARCHAR(256)
# );

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

INSERT INTO role (id, NAME) VALUES
                                (1,'ROLE_admin'),(2,'ROLE_operator'),(3,'ROLE_editor');

INSERT INTO permission_role (PERMISSION_ID, ROLE_ID) VALUES
                                                         (1,1), /*create-> admin */
                                                         (2,1), /* read admin */
                                                         (3,1), /* update admin */
                                                         (4,1), /* delete admin */
                                                         (1,3), /* create editor */
                                                         (4,2),  /* delete operator */
                                                         (3,2);  /* update operator */
insert into user (id, email,password,  enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('1', 'chamath','{bcrypt}$2a$12$v7BWLKlyF5H1k8NBYBvBI.C5SBziVSRZDpmW/6Es9kGpxe3l.dbKu',  true,true,true,true);
insert into  user (id, email,password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('2', 'suranga', '{bcrypt}$2a$12$v7BWLKlyF5H1k8NBYBvBI.C5SBziVSRZDpmW/6Es9kGpxe3l.dbKu',   true,true,true,true);
insert into  user (id, email,password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('3', 'rivindu', '{bcrypt}$2a$12$v7BWLKlyF5H1k8NBYBvBI.C5SBziVSRZDpmW/6Es9kGpxe3l.dbKu',   true,true,true,true);

INSERT INTO role_user (ROLE_ID, USER_ID)
VALUES
    (1, 1) /* 1st-admin */,
    (2, 1) /* 1st-operator */,
    (2, 2) /* 2nd-operator */ ;

UPDATE hibernate_sequence
SET next_val = 7
where next_val = 1;