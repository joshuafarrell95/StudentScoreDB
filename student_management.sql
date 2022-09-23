--Delete any existing DB's, then create and use a new student_management DB
DROP DATABASE IF EXISTS student_management;

CREATE DATABASE student_management;

USE student_management;

--Create a new student_score table
CREATE TABLE student_score (
	Subject VARCHAR(25),
	Score INTEGER
);

--Add all current subjects
INSERT INTO student_score
	(Subject, Score)
	VALUES
	("English", 95),
	("Math", 98),
	("Science", 89);
	
--Display the table
SELECT * FROM student_score;

--Update the Science score
UPDATE student_score
	SET Score = 92
	WHERE Subject = "Science";
	
--Add a Coding subject
INSERT INTO student_score
	VALUES ("Coding", 89);
	
--Delete Math, an old subject
DELETE FROM student_score
	WHERE Subject = "Math";
	
--Display the table with committed updates
SELECT * FROM student_score;