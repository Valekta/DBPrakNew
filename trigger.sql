/*NOT NULL constraint and ID integrity check */
CREATE OR REPLACE FUNCTION check_insert_comment() RETURNS TRIGGER AS
$BODY$
BEGIN
IF (NEW.id IN (SELECT ID FROM Message)) THEN
	RAISE EXCEPTION 'id % already exists for a message', NEW.id;
END IF;
--A comment must be a reply of an other comment or a post
IF (NEW.replyOfComment IS NULL AND NEW.replyOfPost IS NULL) THEN
	RAISE EXCEPTION 'a comment must set a value for replyOfComment or replyOfPost';
END IF;
RETURN NEW;
END;
$BODY$ language plpgsql;

CREATE TRIGGER Add_Comment
BEFORE INSERT ON COMMENT
FOR EACH ROW
EXECUTE PROCEDURE check_insert_comment();

/* ID integrity for places */
CREATE OR REPLACE FUNCTION check_place_id() RETURNS TRIGGER AS
$BODY$
BEGIN
IF (NEW.id IN (SELECT ID FROM Place)) THEN
RAISE EXCEPTION 'id % already exists for a place', NEW.id;
END IF;
RETURN NEW;
END;
$BODY$ language plpgsql;

CREATE TRIGGER CONTINENT_ID
BEFORE INSERT OR UPDATE ON CONTINENT
FOR EACH ROW
EXECUTE PROCEDURE check_place_id();

CREATE TRIGGER COUNTRY_ID
BEFORE INSERT OR UPDATE ON COUNTRY
FOR EACH ROW
EXECUTE PROCEDURE check_place_id();

CREATE TRIGGER CITY_ID
BEFORE INSERT OR UPDATE ON CITY
FOR EACH ROW
EXECUTE PROCEDURE check_place_id();

/* ID integrity for organisations */
CREATE OR REPLACE FUNCTION check_organisation_id() RETURNS TRIGGER AS
$BODY$
BEGIN
IF (NEW.id IN (SELECT ID FROM Organisation)) THEN
RAISE EXCEPTION 'id % already exists for an organisation', NEW.id;
END IF;
RETURN NEW;
END;
$BODY$ language plpgsql;

CREATE TRIGGER UNIVERSITY_ID
BEFORE INSERT OR UPDATE ON UNIVERSITY
FOR EACH ROW
EXECUTE PROCEDURE check_organisation_id();

CREATE TRIGGER COMPANY_ID
BEFORE INSERT OR UPDATE ON COUNTRY
FOR EACH ROW
EXECUTE PROCEDURE check_organisation_id();

/* ID integrity for messages */
CREATE OR REPLACE FUNCTION check_message_id() RETURNS TRIGGER AS
$BODY$
BEGIN
IF (NEW.id IN (SELECT ID FROM Message)) THEN
RAISE EXCEPTION 'id % already exists for a message', NEW.id;
END IF;
RETURN NEW;
END;
$BODY$ language plpgsql;

CREATE TRIGGER COMMENT_ID
BEFORE UPDATE ON COMMENT
FOR EACH ROW
EXECUTE PROCEDURE check_message_id();

CREATE TRIGGER POST_ID
BEFORE INSERT OR UPDATE ON POST
FOR EACH ROW
EXECUTE PROCEDURE check_message_id();