# Auth 2 Security Service
![Open Source Love svg1](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)
![Repo Size](https://img.shields.io/github/repo-size/rivinduchamath/Hello-Hotel)
![count](https://img.shields.io/github/languages/count/rivinduchamath/Hello-Hotel)
![count](https://img.shields.io/github/forks/rivinduchamath/Hello-Hotel?style=social)
![count](https://img.shields.io/github/watchers/rivinduchamath/Hello-Hotel?style=social)

#### Languages and Technology stack 💎💎
![Java](https://img.shields.io/badge/Language-Java_1.8-yellowgreen)
![SQL](https://img.shields.io/badge/Language-SQL-yellowgreen)
![Java Script](https://img.shields.io/badge/Language-Html-yellowgreen)
![CSS](https://img.shields.io/badge/Language-CSS-yellowgreen)

#### Frameworks and Libs
![Spring](https://img.shields.io/badge/Framework-Spring-darkgreen)
![JPAHibernate](https://img.shields.io/badge/Framework-JPAHibernate-darkgreen)
![Bootstrap](https://img.shields.io/badge/Framework-Bootstrap-darkgreen)
![SymanticUI](https://img.shields.io/badge/Framework-Maven-darkgreen)
![JavaMailAPI](https://img.shields.io/badge/Library-SpringSecurity-darkgreen)

####  Databases and servers
![Mysql](https://img.shields.io/badge/Database-MySQL-green)
![Jetty](https://img.shields.io/badge/Server-Jetty-green) 


#### Tools and Technologies Databases and servers

![Spring Boot](https://img.shields.io/badge/Technology-SpringBoot-blue)
![maven](https://img.shields.io/badge/Tool-maven-blue)
![intelij](https://img.shields.io/badge/Tool-intelij-blue)
![postman](https://img.shields.io/badge/Tool-Insomnia-blue)

#### License
[![License](https://img.shields.io/badge/License-Apache%202.0-red.svg)](https://opensource.org/licenses/Apache)

#### User Guide
* Install Java 1.8, Maven, MySQL(8) 

1. Start AuthServerApplication Main Class In Auth Service Which is running Port 9191
2. Start UserServerApplication Main Class In Customer Save Service Which is running Port 8284
3. Start UserServerApplication Main Class In User Service Which is running Port 8283
4. Start UITestApplication Main Class In UI Test Service Which is running Port 8080

#### Open Insomnia
> Method Post (get Auth Token From Auth Service)
1. Open Form 
* grant_type  password
* username    chamath 
* password    1234
* Port Number = http://localhost:9191/oauth/token
 
> output 
> 
        {
            "access_token": "b61e9a7d-0827-4f6e-9b34-c193dcc6451c",
            "token_type": "bearer",
            "refresh_token": "cd299608-9b1c-4958-b9bb-0ee39aad63f6",
            "expires_in": 3599,
            "scope": "READ WRITE"
        }


---------------

> Method Get (get Auth User Details From Auth Service)
1. On URL
* Port Number = http://localhost:9191/oauth/check_token?token=b61e9a7d-0827-4f6e-9b34-c193dcc6451c
> Input Jason

            {
            "aud": [
                "inventory",
                "payment"
                ],
            "user_name": "chamath",
            "scope": [
                    "READ",
                    "WRITE"
                ],
            "active": true,
            "exp": 1658674474,
            "authorities": [
            "ROLE_admin",
            "delete_profile",
            "update_profile",
            "read_profile",
            "create_profile"
        ],
    "client_id": "mobile"
    }






---------------

> Method Get (get All Users From User Service)
1. Open Header
* Authorization  :  bearer b61e9a7d-0827-4f6e-9b34-c193dcc6451c


> output 
> 
       [
        {
        "id": 1,
        "username": "chamath",
        "password": "{bcrypt}$2a$12$v7BWLKlyF5H1k8NBYBvBI.C5SBziVSRZDpmW/6Es9kGpxe3l.dbKu",
        "enabled": true,
        "accountNonExpired": true,
        "credentialsNonExpired": true,
        "accountNonLocked": true,
        "roles": [
            {
            "id": 1,
            "name": "ROLE_admin",
            "permissions": [
            {
            "id": 1,
            "name": "create_profile"
            },
            {
            "id": 2,
            "name": "read_profile"
            },
            {
            "id": 3,
            "name": "update_profile"
            },
            {
            "id": 4,
            "name": "delete_profile"
            }
        ]

> Method Post (save User)

1. Header
* Type    application/json
* If Want You Can add Authorization 
* Authorization bearer 77dcda42-6b67-4c87-9e3b-a038a850ac8a
* If Not Add Auth It will SAve as Normal User
* Port Number = http://localhost:8284/services/customer

> Input Jason
> > 

    {
            "id": 54545,
            "username": "chamathG",
            "password": "{bcrypt}$2a$12$v7BWLKlyF5H1k8NBYBvBI.C5SBziVSRZDpmW/6Es9kGpxe3l.dbKu",
            "enabled": true,
            "accountNonExpired": true,
            "credentialsNonExpired": true,
            "accountNonLocked": true,
            "roles": [
                {
                    "id": 1,
                    "name": "ROLE_admin",
                    "permissions": [
                        {
                            "id": 1,
                            "name": "create_profile"
                        },
                        {
                            "id": 2,
                            "name": "read_profile"
                        },
                        {
                            "id": 3,
                            "name": "update_profile"
                        },
                        {
                            "id": 4,
                            "name": "delete_profile"
                        }
                    ]
                }
            ]
        }



