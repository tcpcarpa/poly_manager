USE polytex_db;

DROP TABLE users;
DROP TABLE configDefinition;
DROP TABLE gruplimit;
DROP TABLE user;
DROP TABLE user_site;
DROP TABLE site;

CREATE TABLE user
(
    user_id  VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    numSites VARCHAR(255)
);

CREATE TABLE site
(
    site_id        VARCHAR(255) PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    mode           VARCHAR(255),
    userdefinition VARCHAR(255),
    numUsers       VARCHAR(255),
    lastUpdate     VARCHAR(255),
    lastlimit      VARCHAR(255),
    last_import    VARCHAR(255),
    path_limitGrup VARCHAR(255),
    path_usuarios  VARCHAR(255),
    fecha_update   VARCHAR(255)
);

CREATE TABLE user_site
(
    username VARCHAR(255),
    site_id  VARCHAR(255)
);

CREATE TABLE configDefinition
(
    userId              INT,
    cardId              INT,
    firstName           INT,
    lastName            INT,
    fullName            INT,
    DepartmentName      INT,
    title               INT,
    effectiveLimitGroup INT,
    futureInactiveData  INT,
    cardId2             INT,
    email               INT,
    dateFormat          VARCHAR(255),
    numFormat           VARCHAR(255),
    site_id             VARCHAR(255) PRIMARY KEY
);

CREATE TABLE users
(
    userId              VARCHAR(255) NOT NULL,
    cardId              VARCHAR(255) NOT NULL,
    firstName           VARCHAR(255),
    lastName            VARCHAR(255),
    fullName            VARCHAR(255),
    DepartmentName      VARCHAR(255) NOT NULL,
    title               VARCHAR(255),
    effectiveLimitGroup VARCHAR(255),
    futureInactiveData  VARCHAR(255),
    cardId2             VARCHAR(255),
    email               VARCHAR(255),
    site_id             VARCHAR(255)
);

CREATE TABLE gruplimit
(
    DepartmentName      VARCHAR(255),
    title               VARCHAR(255),
    effectiveLimitGroup VARCHAR(255),
    site_id             VARCHAR(255)
);

CREATE TABLE tiempo
(
    dia  VARCHAR(255) ,
    hora VARCHAR(255) ,
    minuto VARCHAR(255)
);