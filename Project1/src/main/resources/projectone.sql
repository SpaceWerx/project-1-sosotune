CREATE TABLE ers_users(
id Serial PRIMARY KEY,
username VARCHAR (250) UNIQUE NOT NULL,
password VARCHAR(250) NOT NULL,
role VARCHAR(250) NOT NULL
);

CREATE TABLE ers_reimbursements(
id SERIAL PRIMARY KEY,
author INT NOT NULL,
resolver INT,
description TEXT NOT NULL,
type VARCHAR(250) NOT NULL,
status VARCHAR(250) NOT NULL,
amount FLOAT NOT NULL,

CONSTRAINT fk_author
	FOREIGN KEY (author)
		REFERENCES ers_users(id),
CONSTRAINT fk_resolver
	FOREIGN KEY (resolver)
		REFERENCES ers_users(id)
);

INSERT INTO ers_users (username, password, role)
VALUES('default', 'guest', 'Employee')("admin', 'admin', 'Manager');

--next is used to delete tables and files associated

DROP TABLE IF EXISTS ers_users CASCADE;
DROP TABLE IF EXISTS ers_reimbursements CASCADE;

--making enums

create type role as enum ('EMPLOYEE', 'MANAGER');
create type type as enum ('LODGING', 'TRAVEL', 'FOOD', 'OTHER');
create type status as enum ('PENDING', 'APPROVED', 'DENIED');
