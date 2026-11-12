-- One PostgreSQL server, separate databases: each service only uses its own.
CREATE DATABASE household_db;   -- MS1 (shared)
CREATE DATABASE solar_db;       -- MS3 Solar
CREATE DATABASE marketplace_db; -- MS7 Marketplace
