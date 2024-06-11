CREATE SCHEMA polytex_db;
USE polytex_db;
DROP TABLE users;
DROP TABLE gruplimit;
DROP TABLE site;
CREATE TABLE users
(
    userId                                   VARCHAR(255) PRIMARY KEY,
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
    email                                     VARCHAR(255)
);

CREATE TABLE gruplimit
(
    nom   VARCHAR(255),
    codi  VARCHAR(255) PRIMARY KEY ,
    color VARCHAR(255)
);

CREATE TABLE site
(
    id INT PRIMARY KEY,
    name VARCHAR(255),
    mode VARCHAR(255),
    date date
);