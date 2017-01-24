DROP TABLE mmxml;

CREATE TABLE mmxml (
  id      NUMBER PRIMARY KEY,
  payload XMLTYPE
);

INSERT INTO mmxml VALUES (1, '<?xml version="1.0" encoding="UTF-8"?>
<faculty name="mm">
    <department name="Analytical Information Systems" head="Viacheslav Nesterov" foundation="1971-01-01">
        <students>
            <student name="name7" lastname="lastName7" id="7">
                <courses>
                    <course title="Models and techniques for infromation storage and retrieval" mark="2"/>
                    <course title="Database application development" mark="2"/>
                    <course title="Information storage and management" mark="1"/>
                    <course title="Information retrieval and analysis" mark="2"/>
                </courses>
            </student>
            <student name="name6" lastname="lastName6" id="7">
                <courses>
                    <course title="Software engineering" mark="5"/>
                    <course title="Data mining techniques" mark="4"/>
                    <course title="Data Base" mark="3"/>
                    <course title="Database design" mark="3"/>
                </courses>
            </student>
        </students>
    </department>
    <department name="Computer Science" head="Kosovskii Nikolai K." foundation="1971-01-01">
        <students>
            <student name="name1" lastname="lastName1" id="1">
                <courses>
                    <course title="Verification of Algorithms" mark="5"/>
                    <course title="Discrete Analysis" mark="4"/>
                    <course title="Mathematical Logic" mark="3"/>
                    <course title="Search for Solutions" mark="5"/>
                </courses>
            </student>
            <student name="name2" lastname="lastName2" id="2">
                <courses>
                    <course title="Informatics and Practice on Programming" mark="2"/>
                    <course title="Computer Practice" mark="2"/>
                    <course title="Algebra and Theory of Numbers" mark="2"/>
                    <course title="Informational Teching Technologies" mark="2"/>
                </courses>
            </student>
            <student name="name3" lastname="lastName3" id="3">
                <courses>
                    <course title="Informatics and Practice on Programming" mark="5"/>
                    <course title="Computer Practice" mark="5"/>
                    <course title="Algebra and Theory of Numbers" mark="5"/>
                    <course title="Informational Teching Technologies" mark="5"/>
                </courses>
            </student>
        </students>
    </department>

    <department name="Software Engineering Department" head="Terekhov Andrey N." foundation="1971-01-01">
        <students>
            <student name="name4" lastname="lastName4" id="4">
                <courses>
                    <course title="Architecture of Computers" mark="2"/>
                    <course title="Information Technologies" mark="2"/>
                    <course title="Software Engineering" mark="2"/>
                    <course title="Display of Graphs" mark="2"/>
                </courses>
            </student>
            <student name="name5" lastname="lastName5" id="5">
                <courses>
                    <course title="Introduction to Operational Systems" mark="5"/>
                    <course title="Informatics and Programming" mark="4"/>
                    <course title="Data Base" mark="3"/>
                    <course title="Informatics" mark="5"/>
                </courses>
            </student>
        </students>
    </department>
</faculty>');

CREATE OR REPLACE VIEW departments AS
  SELECT
    name,
    head,
    foundation
  FROM mmxml s, XMLTABLE('/faculty/department'
                         PASSING s.payload
                         COLUMNS name VARCHAR2(128) PATH './@name', head VARCHAR2(128) PATH './@head', foundation DATE
                         PATH './@foundation');

SELECT *
FROM departments;

CREATE OR REPLACE VIEW students AS
  SELECT
    x.id,
    x.lastname,
    x.title,
    x.mark
  FROM mmxml s, XMLTABLE('
for $s in /faculty/department/students/student
for $c in $s/courses/course
return <student>{$s/@lastname, $s/@id, $c/@title, $c/@mark}</student>'
                         PASSING s.payload
                         COLUMNS lastname VARCHAR2(128) PATH './@lastname', id NUMBER PATH './@id', title VARCHAR2(128)
                         PATH './@title', mark NUMBER PATH './@mark'
                ) x;

SELECT *
FROM students;

CREATE OR REPLACE VIEW studentmarks AS
  SELECT
    x.lastname,
    x.mark
  FROM mmxml s, XMLTABLE('
for $s in /faculty/department/students/student
for $c in $s/courses/course
order by $s/@lastname
return <student>{$s/@lastname, $c/@mark}</student>'
                         PASSING s.payload
                         COLUMNS lastname VARCHAR2(128) PATH './@lastname', mark NUMBER PATH './@mark'
                ) x;

SELECT *
FROM studentmarks;

CREATE OR REPLACE VIEW deptcourse AS
  SELECT
    x.dept,
    x.course
  FROM mmxml s, XMLTABLE('
for $d in /faculty/department
for $c in $d/students/student/courses/course
order by $d/@name, $c/@title
return <course dept="{$d/@name}" course="{$c/@title}"></course>'
                         PASSING s.payload
                         COLUMNS dept VARCHAR2(128) PATH './@dept', course VARCHAR2(128) PATH './@course'
                ) x;
SELECT *
FROM deptcourse;