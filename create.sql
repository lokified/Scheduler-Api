CREATE DATABASE scheduler;

\c scheduler;


CREATE TABLE announcements(
    id serial PRIMARY KEY,
    title VARCHAR ,
    userId INTEGER ,
    description VARCHAR
);

CREATE TABLE modules(
    id serial PRIMARY KEY ,
    name VARCHAR
);


CREATE TABLE users(
id serial PRIMARY KEY,
position VARCHAR,
email VARCHAR,
name VARCHAR,
moduleId INTEGER
);

CREATE TABLE sessions(
id serial PRIMARY KEY,
sessionName VARCHAR ,
invitationLink VARCHAR,
startTime TIMESTAMP ,
endTime TIMESTAMP ,
description VARCHAR,
type VARCHAR
 );

CREATE TABLE modules_users(
    id SERIAL PRIMARY KEY,
    moduleId INTEGER,
    userId INTEGER
);

CREATE DATABASE scheduler_test WITH TEMPLATE scheduler;
