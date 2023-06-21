package com.robocode.model.dao.impl;

import com.robocode.model.dao.CourseDAO;
import com.robocode.model.db.DBManager;
import com.robocode.model.entity.Course;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
public class CourseDAOImpl implements CourseDAO {

    private static final String SQL_ERROR = "SQL Error";
    private static final String INSERT = """                        
            INSERT INTO course (id, name, teachername)
            VALUES (default, ?, ?);
            """;

    @Override
    public boolean create(Course entity) {
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getTeacherName());

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
    public Optional<Course> getById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> update(Course entity) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }
}
