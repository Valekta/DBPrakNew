CREATE DOMAIN BIRTHDATE AS DATE CHECK (value < current_date);
CREATE DOMAIN MAILADDRESS AS VARCHAR(100) CHECK (value ~ '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$');