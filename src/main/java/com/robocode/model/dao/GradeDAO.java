package com.robocode.model.dao;

import com.robocode.model.entity.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeDAO extends ObjectDAO<Grade>{

    Optional<Grade> getByStudentIdAndCourseId(long studentId, long courseId);

    List<Grade> getAllByStudentId(long studentId);

}
