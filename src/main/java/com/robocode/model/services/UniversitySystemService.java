package com.robocode.model.services;

import com.robocode.model.dao.CourseDAO;
import com.robocode.model.dao.GradeDAO;
import com.robocode.model.dao.StudentDAO;
import com.robocode.model.entity.Course;
import com.robocode.model.entity.Grade;
import com.robocode.model.entity.Student;
import com.robocode.model.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class UniversitySystemService {
    private final CourseDAO courseDAO;
    private final GradeDAO gradeDAO;
    private final StudentDAO studentDAO;


    public boolean addNewStudent(Student student) {
        if (studentDAO.getByStudentCardId(student.getStudentCardNumber()).isEmpty()){
            return studentDAO.create(student);
        }
        return false;
    }


    public boolean addNewCourse(Course course) {
        return courseDAO.create(course);
    }


    public boolean addStudentOnCourse(Student student, Course course) {
        Grade grade = Grade.builder()
                .course(course)
                .student(student)
                .build();

        return gradeDAO.create(grade);
    }

    public void addMarkToStudent(Student student, Course course, Long mark) throws ObjectNotFoundException {
        Grade grade = gradeDAO.getByStudentIdAndCourseId(student.getId(), course.getId())
                .orElseThrow(ObjectNotFoundException::new);
        grade.setMark(mark);

        gradeDAO.update(grade);
    }

    public List<Student> getStudentListByCourse(Course course) {
        return studentDAO.getAllStudentByCourseId(course.getId());
    }

    public double getStudentAverageGrade(Student student) throws ObjectNotFoundException {
        return gradeDAO.getAllByStudentId(student.getId()).stream()
                .mapToLong(Grade::getMark)
                .average()
                .orElseThrow(() -> new ObjectNotFoundException(student.toString() + "not found"));
    }

    public List<Course> getCourseListByStudent(Student student) {
        List<Course> courseList = new ArrayList<>();
        gradeDAO.getAllByStudentId(student.getId()).stream()
                .map(Grade::getCourse)
                .forEach(courseList::add);

        return courseList;
    }
}
