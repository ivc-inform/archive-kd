SELECT s.sql_fulltext
, ses.username
, ses.osuser
FROM v$sql s
, v$session ses
WHERE ses.sql_address=s.address
and  ses.status='ACTIVE' and nls_lower(ses.program) = nls_lower('JDBC Thin Client')
