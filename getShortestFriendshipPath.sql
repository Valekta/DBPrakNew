CREATE OR REPLACE FUNCTION returnShortestPath(IN pperson BIGINT, IN ffriend BIGINT, OUT shortPath TEXT)
    RETURNS TEXT AS '
BEGIN
    WITH RECURSIVE friendship(pid, firstname, lastname, grade, path, cycle) AS (
    	SELECT p.pid, p.firstname, p.lastname, 1, ARRAY[p.pid], false
    	FROM pkp_symmetric pkp JOIN person p ON pkp.pid = p.pid
    	WHERE pkp.friend = pperson

    	UNION ALL

    	SELECT DISTINCT ON (p.pid) p.pid, p.firstname, p.lastname, (friendship.grade + 1), friendship.path || p.pid, (p.pid = ANY(friendship.path))
    	FROM friendship, pkp_symmetric pkp JOIN person p ON pkp.pid = p.pid
    	WHERE ((pkp.friend = friendship.pid) AND NOT (p.pid = ANY(friendship.path)))
)
SELECT * INTO shortPath
FROM (
    SELECT path
	FROM friendship
	WHERE pid = ffriend
	ORDER BY grade ASC
	LIMIT 1) AS b;
END;
' LANGUAGE plpgsql;