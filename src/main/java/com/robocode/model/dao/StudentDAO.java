package com.robocode.model.dao;

import com.robocode.model.entity.Student;

import java.util.List;

public interface StudentDAO extends ObjectDAO<Student> {

    List<Student> getAllStudentByCourseId(long courseId);
}
