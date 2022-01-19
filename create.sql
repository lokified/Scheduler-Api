CREATE DATABASE scheduler;

\c scheduler;

CREATE TABLE announcements(
    id serial PRIMARY KEY,
    title VARCHAR ,
    userId INTEGER ,
    description VARCHAR
);

CREATE DATABASE scheduler_test WITH TEMPLATE scheduler;