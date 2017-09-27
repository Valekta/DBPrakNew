CREATE VIEW Place AS
(SELECT ID, name FROM CITY)
UNION
(SELECT ID, name FROM COUNTRY)
UNION
(SELECT ID, name FROM CONTINENT);

CREATE VIEW Organisation AS
(SELECT  id, name FROM UNIVERSITY)
UNION
(SELECT  id, name FROM COMPANY);

CREATE VIEW Message AS
(SELECT id, creationDate, browserUsed, locationIP, content, length, creator, locatedIn FROM COMMENT)
UNION
(SELECT id, creationDate, browserUsed, locationIP, content, length, creator, locatedIn FROM POST);

CREATE VIEW Messagetags AS 
(SELECT post, tag FROM POSTTAG)
UNION
(SELECT comment, tag FROM COMMENTTAG);

CREATE VIEW Messagelikes AS
(SELECT post, pid AS Person FROM POSTLIKES)
UNION
(SELECT comment, pid AS Person FROM COMMENTLIKES);

CREATE VIEW pkp_symmetric (pid, friend) AS 
(SELECT pid, knows FROM KNOWS)
Union
(SELECT knows, pid FROM KNOWS)
