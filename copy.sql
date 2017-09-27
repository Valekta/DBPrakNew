/* TAG */
CREATE TEMP TABLE TAG_0 (
    ID INT,
    name VARCHAR(100),
    url VARCHAR(200)
);
 
Copy TAG_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\tag_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO tag(id, name)
SELECT id, name
FROM tag_0;

DROP TABLE TAG_0;

/* tagclass_0_0 */
CREATE TEMP TABLE tagclass_0_0(
    tid INT,
    name VARCHAR(100),
    url VARCHAR(200)
);

Copy tagclass_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\tagclass_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO tagclass(id, name)
SELECT tid, name
FROM tagclass_0_0;

DROP TABLE tagclass_0_0;

/* tag_hasType_tagclass_0_0 */
CREATE TEMP TABLE tag_hasType_tagclass_0_0(
    tid INT,
    tcid INT
);

Copy tag_hasType_tagclass_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\tag_hasType_tagclass_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO tagtype(tag, hastype)
SELECT tid, tcid
FROM tag_hasType_tagclass_0_0;

DROP TABLE tag_hasType_tagclass_0_0;

/* tagclass_isSubclassOf_tagclass_0_0 */
CREATE TEMP TABLE tagclass_isSubclassOf_tagclass_0_0(
    tid INT,
    tcid INT
);

COPY tagclass_isSubclassOf_tagclass_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\tagclass_isSubclassOf_tagclass_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO tagclasstype(tagclass, hastype)
SELECT tid, tcid
FROM tagclass_isSubclassOf_tagclass_0_0;

DROP TABLE tagclass_isSubclassOf_tagclass_0_0;

/* continent */
CREATE TEMP TABLE CONTINENT_0 (
    ID INT,
    name VARCHAR(100),
    url VARCHAR(200),
    type VARCHAR(30),
    isPartOf INT
);
 
Copy CONTINENT_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\place_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO continent(id, name)
SELECT id, name
FROM CONTINENT_0
WHERE type ='continent';

DROP TABLE CONTINENT_0;

/* country */
CREATE TEMP TABLE COUNTRY_0 (
    ID INT,
    name VARCHAR(100),
    url VARCHAR(200),
    type VARCHAR(30),
    isPartOf INT
);
 
Copy COUNTRY_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\place_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO country(id, name, continent)
SELECT id, name, isPartOf
FROM COUNTRY_0
WHERE type ='country';

DROP TABLE COUNTRY_0;

/* city */ 
CREATE TEMP TABLE CITY_0 (
    ID INT,
    name VARCHAR(100),
    url VARCHAR(200),
    type VARCHAR(30),
    isPartOf INT
);
 
Copy CITY_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\place_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO city(id, name, country)
SELECT id, name, isPartOf
FROM CITY_0
WHERE type ='city';

DROP TABLE CITY_0;

/* person_0_0.csv */
CREATE TEMP TABLE person_0_0(
    id BIGINT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    gender VARCHAR(30),
    birthday DATE,
    creationDate TIMESTAMP,
    locationIP INET,
    browserUsed VARCHAR(50),
    place BIGINT
);

Copy person_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\person_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO person(pid, firstName, lastName, gender, birthday, locationIP, browserUsed, creationDate, locatedIn)
SELECT id, firstName, lastName, gender, birthday, locationIP, browserUsed, creationDate, place
FROM person_0_0;

DROP TABLE person_0_0;

/* person_speaks_language_0_0 */
CREATE TEMP TABLE person_speaks_language_0_0(
    pid BIGINT,
    language CHAR(2)
);

COPY person_speaks_language_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\person_speaks_language_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO LANGUAGE (ISOcode, speaker)
SELECT language, pid
FROM person_speaks_language_0_0;

DROP TABLE person_speaks_language_0_0;

/*  person_email_emailaddress_0_0 */
CREATE TEMP TABLE person_email_emailaddress_0_0(
    id  BIGINT,
    email VARCHAR(100)
);

COPY person_email_emailaddress_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\person_email_emailaddress_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO EMAIL(address, owner)
SELECT email, id
FROM person_email_emailaddress_0_0;

DROP TABLE person_email_emailaddress_0_0;

/* forum */
CREATE TEMP TABLE forum_0_0(
    id BIGINT,
    title VARCHAR(100),
    creationDate TIMESTAMP,
    moderator BIGINT
);

Copy forum_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\forum_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO FORUM(id, title, creationDate, moderator)
SELECT id, title, creationDate, moderator
FROM forum_0_0;

DROP TABLE forum_0_0;

/* post_0_0 */
CREATE TEMP TABLE post_0_0 (
    ID BIGINT,
    imageFile VARCHAR(200),
    creationDate TIMESTAMP,
    locationIP INET,
    browserUsed VARCHAR(50),
    language CHAR(2),
    content TEXT,
    length INT,
    creator BIGINT,
    fid BIGINT,
    place INT
);
 
Copy post_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\post_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO post(id, imagefile, creationDate, browserUsed, locationIP, language, content, length, creator, forum, locatedIn)
SELECT id, imageFile, creationDate, browserUsed, locationIP, language, content, length, creator, fid, place
FROM post_0_0;

DROP TABLE post_0_0;

/* comment */
CREATE TEMP TABLE comment_0_0 (
    ID BIGINT,
    creationDate TIMESTAMP,
    locationIP INET,
    browserUsed VARCHAR(50),
    content TEXT,
    length INT,
    creator BIGINT,
    place INT,
    replyOfPost BIGINT,
    replyOfComment BIGINT
);

Copy comment_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\comment_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO comment(id, creationDate, locationIP, browserUsed, content, length, creator, locatedIn, replyOfPost, replyOfComment)
SELECT id, creationDate, locationIP, browserUsed, content, length, creator, place, replyOfPost,replyOfComment
FROM comment_0_0;

DROP TABLE comment_0_0;

/* company_0_0 */
CREATE TEMP TABLE company_0_0(
    id SERIAL,
    type VARCHAR(200),
    name VARCHAR(150),
    url VARCHAR(200),
    place BIGINT
);

Copy company_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\organisation_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO COMPANY(id, name, locatedIn)
SELECT id, name, place
FROM company_0_0
WHERE type='company';

DROP TABLE company_0_0;

/* university_0_0 */
CREATE TEMP TABLE university_0_0(
    id SERIAL,
    type VARCHAR(200),
    name VARCHAR(150),
    url VARCHAR(200),
    place BIGINT
);

Copy university_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\organisation_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO UNIVERSITY(id, name, locatedIn)
SELECT id, name, place
FROM university_0_0
WHERE type = 'university';

DROP TABLE university_0_0;

/* comment_hastag */
CREATE TEMP TABLE comment_hasTag_0 (
    cid BIGINT,
    tid BIGINT
);

Copy comment_hasTag_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\comment_hasTag_tag_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO commentTag(comment, tag)
SELECT cid, tid
FROM comment_hasTag_0;

DROP TABLE comment_hasTag_0;

/* forum_hasMember_person_0_0 */
CREATE TEMP TABLE forum_hasMember_person_0_0(
    fid BIGINT,
    pid BIGINT,
    joinDate TIMESTAMP
);

Copy forum_hasMember_person_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\forum_hasMember_person_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO FORUMMEMBER(forum, pid, joinDate)
SELECT fid, pid, joinDate
FROM forum_hasMember_person_0_0;

DROP TABLE forum_hasMember_person_0_0;

/* person_hasInterest_tag_0_0 */
CREATE TEMP TABLE person_hasInterest_tag_0_0(
    pid BIGINT,
    tid INT
);

Copy person_hasInterest_tag_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\person_hasInterest_tag_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO INTEREST(pid, tag)
SELECT pid, tid
FROM person_hasInterest_tag_0_0;

DROP TABLE person_hasInterest_tag_0_0;

/* person_knows_person_0_0.csv */
CREATE TEMP TABLE person_knows_person_0_0(
    pid BIGINT,
    knows BIGINT, 
    creationDate TIMESTAMP
);

COPY person_knows_person_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\person_knows_person_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO KNOWS(pid, knows, creationDate)
SELECT pid, knows, creationDate
FROM person_knows_person_0_0;

DROP TABLE person_knows_person_0_0;

/* forum_hasTag_tag_0_0 */
CREATE TEMP TABLE forum_hasTag_tag_0_0(
    fid BIGINT,
    tid BIGINT
);

Copy forum_hasTag_tag_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\forum_hasTag_tag_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO FORUMTAG(forum, tag)
SELECT fid, tid
FROM forum_hasTag_tag_0_0;

DROP TABLE forum_hasTag_tag_0_0;

/* person_likes_comment_0_0 */
CREATE TEMP TABLE person_likes_comment_0_0(
    pid BIGINT,
    cid BIGINT,
    creationDate TIMESTAMP
);

COPY person_likes_comment_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\person_likes_comment_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO COMMENTLIKES(pid, comment, creationDate)
SELECT pid, cid, creationDate
FROM person_likes_comment_0_0;

DROP TABLE person_likes_comment_0_0;

/* person_likes_post_0_0 */
CREATE TEMP TABLE person_likes_post_0_0(
    person_id BIGINT,
    post_id BIGINT,
    creationDate TIMESTAMP
);

COPY person_likes_post_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\person_likes_post_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO POSTLIKES(pid, post, creationDate)
SELECT person_id, post_id, creationDate
FROM person_likes_post_0_0;

DROP TABLE person_likes_post_0_0;

/* person_studyAt_organisation_0_0 */
CREATE TEMP TABLE person_studyAt_organisation_0_0(
    pid BIGINT,
    orid INT,
    classYear INT
);

COPY person_studyAt_organisation_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\person_studyAt_organisation_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO STUDYAT(pid, university, classYear)
SELECT pid, orid, classYear
FROM person_studyAt_organisation_0_0;

DROP TABLE person_studyAt_organisation_0_0;

/* person_workAt_organisation_0_0 */
CREATE TEMP TABLE person_workAt_organisation_0_0(
    pid BIGINT,
    orid INT,
    workFrom INT
);

COPY person_workAt_organisation_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\person_workAt_organisation_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO WORKAT(pid, company, workFrom)
SELECT pid, orid, workFrom
FROM person_workAt_organisation_0_0;

DROP TABLE person_workAt_organisation_0_0;

/* post_hasTag_tag_0_0.csv */
CREATE TEMP TABLE post_hasTag_tag_0_0(
    post_id BIGINT,
    tid INT
);

COPY post_hasTag_tag_0_0 FROM 'E:\Informatik\Datenbankpraktikum\social_network\post_hasTag_tag_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO postTag(post, tag)
SELECT post_id, tid
FROM post_hasTag_tag_0_0;

DROP TABLE post_hasTag_tag_0_0;