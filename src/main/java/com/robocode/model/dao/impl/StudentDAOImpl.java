package com.robocode.model.dao.impl;

import com.robocode.model.dao.StudentDAO;
import com.robocode.model.entity.Student;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean create(Student entity) {
        return false;
    }

    @Override
    public Student getById(long id) {
        return null;
    }

    @Override
    public Student update(Student entity) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public List<Student> getAllStudentByCourseId(long courseId) {
        return null;
    }
}
