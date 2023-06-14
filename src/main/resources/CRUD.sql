INSERT INTO student (name, surname, student_card_number)
VALUES (?, ?, ?);

SELECT *
FROM student
WHERE id = ?;

SELECT *
FROM student;

DELETE
FROM student
WHERE id = ?;

UPDATE student
SET name = ?, surname = ?, student_card_number = ?
WHERE id = ?;