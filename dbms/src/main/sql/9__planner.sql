CREATE TABLE points (
  x NUMBER,
  y NUMBER
);

INSERT INTO points (x, y) VALUES (dbms_random.VALUE(0, 100), dbms_random.VALUE(0, 100));

SELECT x, y
FROM points;

TRUNCATE TABLE points;

BEGIN
  DBMS_SCHEDULER.CREATE_JOB
  (job_name => 'insert_job',
   job_type => 'PLSQL_BLOCK',
   job_action => 'INSERT INTO points (x, y) VALUES (dbms_random.VALUE(0, 100), dbms_random.VALUE(0, 100));',
   enabled => TRUE,
   repeat_interval =>'FREQ=SECONDLY;INTERVAL=1'
  );
END;

BEGIN
  DBMS_SCHEDULER.DROP_JOB('insert_job', TRUE);
END;