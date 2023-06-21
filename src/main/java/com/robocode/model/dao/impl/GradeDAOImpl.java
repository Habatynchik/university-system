package com.robocode.model.dao.impl;

import com.robocode.model.dao.GradeDAO;
import com.robocode.model.db.DBManager;
import com.robocode.model.entity.Grade;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class GradeDAOImpl implements GradeDAO {

    private static final String SQL_ERROR = "SQL Error";
    private static final String INSERT = """                        
            INSERT INTO grade (id, student_id, course_id, mark)
            VALUES (default, ?, ?, ?);
            """;

    @Override
    public Optional<Grade> getByStudentIdAndCourseId(long studentId, long courseId) {
        return Optional.empty();
    }

    @Override
    public List<Grade> getAllByStudentId(long studentId) {
        return null;
    }


    @Override
    public boolean create(Grade entity) {
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setLong(1, entity.getStudent().getId());
            statement.setLong(2, entity.getCourse().getId());
            statement.setLong(3, Optional.ofNullable(entity.getMark()).orElse(0L));

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error(SQL_ERROR);
            return false;
        }
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
