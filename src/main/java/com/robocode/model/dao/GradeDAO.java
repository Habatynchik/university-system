package com.robocode.model.dao;

import com.robocode.model.entity.Grade;

import java.util.List;

public interface GradeDAO extends ObjectDAO<Grade>{

    Grade getByStudentIdAndCourseId(long studentId, long courseId);

    List<Grade> getAllByStudentId(long studentId);

}
