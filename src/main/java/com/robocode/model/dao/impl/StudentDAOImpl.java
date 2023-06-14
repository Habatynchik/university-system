package com.robocode.model.dao.impl;

import com.robocode.model.dao.StudentDAO;
import com.robocode.model.db.DBManager;
import com.robocode.model.entity.Student;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class StudentDAOImpl implements StudentDAO {

    private static final String SELECT_BY_ID = """
            SELECT *
            FROM student
            WHERE id = ?;
            """;

    private static final String SELECT_BY_STUDENT_CARD = """
            SELECT *
            FROM student
            WHERE student_card_number = ?;
            """;
    private static final String INSERT = """                        
            INSERT INTO student (name, surname, student_card_number)
            VALUES (?, ?, ?);
            """;
    private static final String DELETE_BY_ID = """
            DELETE
            FROM student
            WHERE id = ?;
            """;
    private static final String UPDATE_BY_ID = """
            UPDATE student
            SET name = ?, surname = ?, student_card_number = ?
            WHERE id = ?;
            """;

    @Override
    public boolean create(Student entity) {

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setLong(3, entity.getStudentCardNumber());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("SQL Error");
            return false;
        }
    }

    @Override
    public Optional<Student> getByStudentCardId(long studentCardId) {

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_STUDENT_CARD)) {

            statement.setLong(1, studentCardId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.ofNullable(Student.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .studentCardNumber(resultSet.getLong(4))
                        .build());
            }

        } catch (SQLException e) {
            log.error("SQL Error");
            return Optional.empty();
        }
        return Optional.empty();
    }

    /*
     * TODO realize this method
     * */
    @Override
    public Optional<Student> getById(long id) {
        return Optional.empty();
    }

    /*
     * TODO realize this method
     * */
    @Override
    public Optional<Student> update(Student entity) {
        return Optional.empty();
    }

    /*
     * TODO realize this method
     * */
    @Override
    public boolean deleteById(long id) {
        return false;
    }

    /*
     * TODO realize this method
     * */
    @Override
    public List<Student> getAllStudentByCourseId(long courseId) {
        return null;
    }


}
