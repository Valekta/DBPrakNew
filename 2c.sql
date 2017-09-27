﻿CREATE TABLE NotWorkAt (
	PID BIGINT,
	company INT,
	workFrom INT,
	deleteFrom TIMESTAMP,
	PRIMARY KEY (PID, company),
	FOREIGN KEY (PID) REFERENCES PERSON
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY (company) REFERENCES Company
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE OR REPLACE FUNCTION check_employee() RETURNS TRIGGER AS
$BODY$
BEGIN
INSERT INTO NotWorkAt(PID, company, workFrom, deletefrom)
VALUES(Old.PID, old.company, old.workFrom, now());
RETURN OLD;
END;
$BODY$ language plpgsql;


CREATE TRIGGER DELETE_EMPLOYEE
AFTER DELETE ON WORKAT
FOR EACH ROW
EXECUTE PROCEDURE check_employee();
