CREATE DATABASE scheduler;

\c scheduler;

CREATE TABLE users(
id serial PRIMARY KEY,
position VARCHAR,
email VARCHAR,
name VARCHAR,
moduleId int
);

CREATE TABLE sessions(
id serial PRIMARY KEY,
invitationLink VARCHAR,
sessionTime TIMESTAMP,
type VARCHAR,
description VARCHAR,
comments VARCHAR);

CREATE DATABASE scheduler_test WITH TEMPLATE scheduler_test;