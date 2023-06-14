package com.robocode.dao;

import com.robocode.model.dao.CourseDAO;
import com.robocode.model.dao.GradeDAO;
import com.robocode.model.dao.StudentDAO;
import com.robocode.model.dao.impl.CourseDAOImpl;
import com.robocode.model.dao.impl.GradeDAOImpl;
import com.robocode.model.dao.impl.StudentDAOImpl;
import com.robocode.model.entity.Student;
import com.robocode.model.services.UniversitySystemService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UniversitySystemServiceTest {
    static CourseDAO courseDAO;
    static StudentDAO studentDAO;
    static GradeDAO gradeDAO;
    static UniversitySystemService universitySystemService;

    Student testStudent;

    @BeforeAll
    static void setUp() {
        courseDAO = new CourseDAOImpl();
        studentDAO = new StudentDAOImpl();
        gradeDAO = new GradeDAOImpl();

        universitySystemService = new UniversitySystemService(courseDAO, gradeDAO, studentDAO);
    }

    @BeforeEach
    void beforeEach() {
        testStudent = Student.builder()
                .name("Dima")
                .surname("Mokhonko")
                .studentCardNumber(1L)
                .build();
    }

    @Test
    void createTest() {
        assertTrue(universitySystemService.addNewStudent(testStudent));
    }

    @Test
    void createTestWithDuplicateStudentCardNumber() {
        assertFalse(universitySystemService.addNewStudent(testStudent));
    }

}
