create table student
(
    id                  SERIAL  NOT NULL,
    name                VARCHAR NOT NULL,
    surname             VARCHAR NOT NULL,
    student_card_number INT     NOT NULL,

    PRIMARY KEY (id)
);

create table course
(
    id          SERIAL  NOT NULL,
    name        VARCHAR NOT NULL,
    teacherName VARCHAR NOT NULL,

    PRIMARY KEY (id)
);

create table grade
(
    id         SERIAL NOT NULL,
    mark       INT,
    student_id INT    NOT NULL REFERENCES student (id) ON DELETE CASCADE,
    course_id  INT    NOT NULL REFERENCES course (id) ON DELETE CASCADE,

    PRIMARY KEY (id)
)