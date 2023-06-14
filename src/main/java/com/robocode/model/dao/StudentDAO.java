package com.robocode.model.dao;

import com.robocode.model.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDAO extends ObjectDAO<Student> {

    List<Student> getAllStudentByCourseId(long courseId);

    Optional<Student> getByStudentCardId(long studentCardId);
}
