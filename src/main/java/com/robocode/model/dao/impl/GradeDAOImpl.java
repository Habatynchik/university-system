package com.robocode.model.dao.impl;

import com.robocode.model.dao.GradeDAO;
import com.robocode.model.entity.Grade;

import java.util.List;

public class GradeDAOImpl implements GradeDAO {
    @Override
    public boolean create(Grade entity) {
        return false;
    }

    @Override
    public Grade getById(long id) {
        return null;
    }

    @Override
    public Grade update(Grade entity) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public Grade getByStudentIdAndCourseId(long studentId, long courseId) {
        return null;
    }

    @Override
    public List<Grade> getAllByStudentId(long studentId) {
        return null;
    }
}
