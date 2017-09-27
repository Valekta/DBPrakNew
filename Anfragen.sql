-- 1
SELECT count (distinct city.id)
FROM city JOIN country ON city.country = country.id 
   JOIN continent ON country.continent = continent.id 
   JOIN University ON city.id = locatedIn
WHERE continent.name = 'Africa';
/* Anzahl Tupel: 1
count
100
*/

-- 2
SELECT firstName|| ' ' || lastName AS name, count(id) AS Forenbeiträge
FROM person JOIN post ON pid = creator
WHERE birthday = (SELECT MAX(birthday) FROM person)
GROUP BY pid;
/* Anzahl Tupel: 1
name|forenbeiträge
'Paul Becker'|232
*/

-- 3
SELECT co.id, co.name, COALESCE(count(replyOfPost),0) AS Kommentaranzahl
FROM country co LEFT OUTER JOIN comment cm ON co.id = locatedIn
GROUP BY co.id
ORDER BY Kommentaranzahl;
/* Anzahl Tupel: 111
id|name|kommentaranzahl
31|'Northern_Ireland'|0
75|'England"|0
48|'Wales"|0
40|'Scotland'|0
88|'Nepal"'|4
102|'Tanzania'|4
16|'Hong_Kong'|4
41|'Singapore'|5
29|'New_Zealand'|5
*/

--4
SELECT city.name, COUNT(*) AS Einwohnerzahl
FROM person JOIN city ON locatedIn = id
GROUP BY id
HAVING COUNT(*) >= ALL (SELECT COUNT(locatedIn)
			FROM person JOIN city ON locatedIn = id
			GROUP BY id);
/* Anzahl Tupel: 2
name|einwohnerzahl
'Rahim_Yar_Khan'|2
'Ludwigsburg'|2
*/

-- 5
SELECT pid AS person, friend
FROM pkp_symmetric JOIN person USING (pid)
WHERE firstName = 'Hans' AND lastName = 'Johansson';
/* Anzahl Tupel: 12
person|friend
5497558138908|5497558138940
5497558138908|7696581394474
5497558138908|3298534883392
5497558138908|9895604650000
5497558138908|5497558138885
5497558138908|10995116277764
5497558138908|12094627905550
5497558138908|12094627905563
5497558138908|3298534883405
5497558138908|12094627905567
5497558138908|12094627905628
5497558138908|8796093022217
*/

-- 6
SELECT DISTINCT p2.lastName, p2.firstName
FROM person p1 JOIN pkp_symmetric sym1 USING (pid) 
   JOIN pkp_symmetric sym2 ON sym1.friend = sym2.pid 
   JOIN person p2 ON sym2.friend = p2.pid
WHERE p1.firstName = 'Hans' AND p1.lastName = 'Johansson' AND p2.firstName != 'Hans' AND p2.lastName != 'Johansson' 
   AND sym2.friend NOT IN (SELECT friend
            FROM person p JOIN pkp_symmetric USING (pid)
            WHERE firstName = 'Hans' AND lastName = 'Johansson')
ORDER BY p2.lastName;
/* Anzahl Tupel: 47
lastname|firstname
'Abdallahi'|'Yahya Ould Ahmed El'
'Abouba'|'Ali'
'Alkaios'|'Evangelos'
'Bazayev'|'Oleg'
'Bernal'|'Pablo'
'Bravo'|'Adrian'
'Burak'|'Jimmy'
'Chen'|'Amy'
'Chen'|'Anson'
*/

-- 7
SELECT firstName|| ' ' || lastName AS name
FROM person p
WHERE NOT EXISTS (SELECT *
		  FROM forummember fm
		  WHERE pid = (SELECT pid FROM person WHERE firstName = 'Mehmet' AND lastName = 'Koksal')
		  AND forum NOT IN (SELECT forum FROM forummember fm WHERE fm.pid = p.pid)
);
/* Anzahl Tupel: 4
name
'Chen Yang'
'Mehmet Koksal'
'Paul Becker'
'Miguel Gonzalez'
*/

-- 8
SELECT continent.name, round(((count(*)*100.0) / (SELECT count(*) FROM PERSON)),2) AS Nutzeranteil_in_Prozent
FROM person JOIN city ON person.locatedIn = city.id
       JOIN country ON city.country = country.id 
       JOIN continent ON country.continent = continent.id
GROUP BY continent.name;
/* Anzahl Tupel: 5
name|nutzeranteil_in_prozent
'North_America'|9.09
'Asia'|50.00
'South_America'|4.55
'Africa'|11.36
'Europe'|25.00
*/

-- 9
SELECT name, count(*) AS Häufigkeit
FROM posttag JOIN tagtype USING (tag) JOIN tagclass ON tagtype.hastype = tagclass.id
GROUP BY name
ORDER BY count(*) DESC
LIMIT 10;
/* Anzahl Tupel: 10
name|häufigkeit
'Person'|110
'MusicalArtist'|99
'OfficeHolder'|76
'Writer'|66
'TennisPlayer'|63
'BritishRoyalty'|57
'Saint'|33
'Single'|30
'Philosopher'|28
'Album'|27
*/

-- 10
SELECT DISTINCT firstName, lastName
FROM person p JOIN message m ON creator = pid
WHERE creator NOT IN (SELECT creator 
		      FROM message m JOIN messagelikes ml ON m.id = ml.post)
ORDER BY lastName;
/* Anzahl Tupel: 27
'firstname'|'lastname'
'Ayesha'|'Ahmed'
'Mirza Kalich'|'Ali'
'Pablo'|'Bernal'
'Luigi'|'Colombo'
'Bryn'|'Davies'
'Abdoulaye Khouma'|'Dia'
'Roberto'|'Diaz'
'Albaye Papa'|'Diouf'
'Aleksandr'|'Dobrunov'
*/

-- 11
SELECT title
FROM forum JOIN post ON forum.id = post.forum
GROUP BY forum.id
HAVING count(*) > ((SELECT count(*) FROM post)/(SELECT count(*) FROM forum))
ORDER BY title;
/* Anzahl Tupel: 359
title
'Album 0 of Abdul Haris Tobing'
'Album 0 of Alejandro Rodriguez'
'Album 0 of Ali Abouba'
'Album 0 of Amy Chen'
'Album 0 of Celso Oliveira'
'Album 0 of Djelaludin Zaland'
'Album 0 of Eric Mettacara'
'Album 0 of Fritz Engel'
'Album 0 of Hao Li'
*/

-- 12
SELECT lastname, firstname
FROM (Select creator, post.id, count(*)
      FROM post JOIN postlikes ON post.id = postlikes.post
      GROUP BY post.id
      ORDER BY count(*) DESC
      LIMIT 1) AS mostlikes 
      JOIN pkp_symmetric ON mostlikes.creator = pid JOIN person ON pkp_symmetric.friend = person.pid
ORDER BY lastname;
/* Anzahl Tupel: 23
lastname|firstname
'Abouba'|'Ali'
'Achiou'|'Ali'
'Chen'|'Cheng'
'Davies'|'Bryn'
'Fischer'|'Karl'
'Forouhar'|'Hossein'
'Hu'|'Jun'
'Hu'|'Wei'
'Johansson'|'Hans'
*/

-- 13
WITH RECURSIVE Freundeslinie (pid, distanz) AS
(SELECT pid, 1 FROM pkp_symmetric s WHERE s.friend = '94'
UNION
SELECT sym.pid, distanz+1  FROM pkp_symmetric sym JOIN Freundeslinie fl ON sym.friend = fl.pid WHERE distanz < 100)
SELECT distinct pid, min(distanz) AS distanz FROM Freundeslinie
WHERE pid <> 94
GROUP BY pid;
/* Anzahl Tupel: 72
pid|distanz
15393162788920|3
6597069766688|3
3298534883350|2
13194139533352|2
8796093022251|1
9895604650074|3
2199023255625|1
14293651161159|3
2199023255628|2
10995116277811|3
*/

-- 14
WITH RECURSIVE Freundeslinie (pid, distanz, pfad) AS
(SELECT pid, 1 AS distanz, pid || '-' || friend AS pfad FROM pkp_symmetric s WHERE s.friend = '94'
UNION
SELECT sym.pid, distanz+1, pfad || '-' || sym.friend AS pfad FROM pkp_symmetric sym JOIN Freundeslinie fl ON sym.friend = fl.pid WHERE distanz < 4)
SELECT pid, distanz, pfad FROM Freundeslinie
/* Anzahl Tupel: 6695
pid|distanz|pfad
8796093022217|1|'8796093022217-94'
96|1|'96-94'
2199023255625|1|'2199023255625-94'
10995116277851|1|'10995116277851-94'
8796093022251|1|'8796093022251-94'
3298534883365|1|'3298534883365-94'
16492674416689|2|'8796093022217-94-8796093022217'
2199023255611|2|'2199023255625-94-2199023255625'
9895604650036|2|'10995116277851-94-10995116277851'
13194139533342|2|'8796093022217-94-8796093022217'
*/
