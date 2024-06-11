CREATE SCHEMA polytex_db;
USE polytex_db;

DROP TABLE users;
DROP TABLE user_definition;
DROP TABLE gruplimit;
DROP TABLE site;
DROP TABLE user;
DROP TABLE user_site;

CREATE TABLE users
(
    userId                                    VARCHAR(255) PRIMARY KEY,
    cardId                                    VARCHAR(255),
    firstName                                 VARCHAR(255),
    lastName                                  VARCHAR(255),
    DepartmentName                            VARCHAR(255),
    title                                     VARCHAR(255),
    effectiveLimitGroup                       VARCHAR(255),
    isDisabledOrDisabledDate                  VARCHAR(255),
    simpleOrExtendedModeQuantityBalance       VARCHAR(255),
    rfidModeItemNameGroupSHORTQuantitybalance VARCHAR(255),
    cardId2                                   VARCHAR(255),
    email                                     VARCHAR(255),
    site_id                                   VARCHAR(255),
    CONSTRAINT fk_site_users FOREIGN KEY (site_id) REFERENCES site (site_id)
);

CREATE TABLE site
(
    site_id        VARCHAR(255) PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    mode           VARCHAR(255),
    settings       VARCHAR(255),
    separador      VARCHAR(255),
    userdefinition BOOLEAN      NOT NULL UNIQUE,
    lastUpdate     VARCHAR(255),
    numUsers       VARCHAR(255),
    lastlimit      VARCHAR(255)
);

CREATE TABLE user
(
    user_id    INT AUTO_INCREMENT,
    username   VARCHAR(255) NOT NULL PRIMARY KEY,
    password   VARCHAR(255) NOT NULL,
    numSites   VARCHAR(255)
);

CREATE TABLE user_site (
                           username VARCHAR(255) PRIMARY KEY ,
                           site_id VARCHAR(255),
                           UNIQUE (username, site_id)
);

CREATE TABLE user_definition
(
    userId                                    INT,
    cardId                                    INT,
    firstName                                 INT,
    lastName                                  INT,
    DepartmentName                            INT,
    title                                     INT,
    effectiveLimitGroup                       INT,
    isDisabledOrDisabledDate                  INT,
    simpleOrExtendedModeQuantityBalance       INT,
    rfidModeItemNameGroupSHORTQuantitybalance INT,
    cardId2                                   INT,
    email                                     INT,
    site_id                                   VARCHAR(255) PRIMARY KEY,
    CONSTRAINT fk_site_userdef FOREIGN KEY (site_id) REFERENCES site (site_id)

);

CREATE TABLE gruplimit
(
    nom     VARCHAR(255),
    codi    VARCHAR(255),
    color   VARCHAR(255),
    site_id VARCHAR(255) PRIMARY KEY,
    lastupdate DATE,
    CONSTRAINT fk_site_grup FOREIGN KEY (site_id) REFERENCES site (site_id)


);