CREATE DATABASE scheduler;

\c scheduler;

<<<<<<< HEAD
CREATE TABLE announcements(
    id serial PRIMARY KEY,
    title VARCHAR ,
    userId INTEGER ,
    description VARCHAR
);

CREATE DATABASE scheduler_test WITH TEMPLATE scheduler;
=======
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

CREATE DATABASE scheduler_test WITH TEMPLATE scheduler;
>>>>>>> 1f4b7b72cc556d5ef0fe6b563dae6dde4ce02336
