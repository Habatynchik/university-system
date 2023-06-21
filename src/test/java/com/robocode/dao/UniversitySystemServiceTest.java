package com.robocode.dao;

import com.robocode.model.dao.CourseDAO;
import com.robocode.model.dao.GradeDAO;
import com.robocode.model.dao.StudentDAO;
import com.robocode.model.dao.impl.CourseDAOImpl;
import com.robocode.model.dao.impl.GradeDAOImpl;
import com.robocode.model.dao.impl.StudentDAOImpl;
import com.robocode.model.db.DBManager;
import com.robocode.model.entity.Course;
import com.robocode.model.entity.Student;
import com.robocode.model.services.UniversitySystemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UniversitySystemServiceTest {
    static CourseDAO courseDAO;
    static StudentDAO studentDAO;
    static GradeDAO gradeDAO;
    static UniversitySystemService universitySystemService;

    Student student;
    Course course;

    @BeforeAll
    static void setUp() {
        initSchema();

        courseDAO = new CourseDAOImpl();
        studentDAO = new StudentDAOImpl();
        gradeDAO = new GradeDAOImpl();

        universitySystemService = new UniversitySystemService(courseDAO, gradeDAO, studentDAO);
    }

    @BeforeEach
    void beforeEach() {
        student = Student.builder()
                .id(1L)
                .name("Dima")
                .surname("Mokhonko")
                .studentCardNumber(1L)
                .build();

        course = Course.builder()
                .id(1L)
                .name("Math")
                .teacherName("teacher")
                .build();
    }

    @Test
    void createTest() {
        assertTrue(universitySystemService.addNewStudent(student));
    }

    @Test
    void createTestWithDuplicateStudentCardNumber() {
        assertFalse(universitySystemService.addNewStudent(student));
    }

    @Test
    void addNewCourseTest() {
        assertTrue(universitySystemService.addNewCourse(course));
    }


    private static void initSchema() {
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("""
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
                                     """)) {

            statement.execute();
        } catch (SQLException e) {

        }
    }
}
