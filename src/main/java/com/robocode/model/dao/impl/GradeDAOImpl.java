package com.robocode.model.dao.impl;

import com.robocode.model.dao.GradeDAO;
import com.robocode.model.entity.Grade;

import java.util.List;
import java.util.Optional;

public class GradeDAOImpl implements GradeDAO {

    @Override
    public Optional<Grade> getByStudentIdAndCourseId(long studentId, long courseId) {
        return Optional.empty();
    }

    @Override
    public List<Grade> getAllByStudentId(long studentId) {
        return null;
    }

    /*
     * TODO realize this method
     * */
    @Override
    public boolean create(Grade entity) {
        return false;
    }

    /*
     * TODO realize this method
     * */
    @Override
    public Optional<Grade> getById(long id) {
        return Optional.empty();
    }

    /*
     * TODO realize this method
     * */
    @Override
    public Optional<Grade> update(Grade entity) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }
}
