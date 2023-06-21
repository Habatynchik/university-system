package com.robocode.model.dao.impl;

import com.robocode.model.dao.StudentDAO;
import com.robocode.model.db.DBManager;
import com.robocode.model.entity.Student;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class StudentDAOImpl implements StudentDAO {

    private static final String SQL_ERROR = "SQL Error";
    private static final String SELECT_BY_COURSE_ID = """
            SELECT s.* FROM student s
            JOIN grade g on s.id = g.student_id
            WHERE g.course_id = ?;
            """;

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
            log.error(SQL_ERROR);
            return false;
        }
    }

    @Override
    public Optional<Student> getByStudentCardId(long studentCardId) {
        return getStudentByIdAndQuery(studentCardId, SELECT_BY_STUDENT_CARD);
    }

    @Override
    public Optional<Student> getById(long id) {
        return getStudentByIdAndQuery(id, SELECT_BY_ID);

    }

    private Optional<Student> getStudentByIdAndQuery(long id, String query) {
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(4, id);
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
            log.error(SQL_ERROR);
            return Optional.empty();
        }

        return Optional.empty();
    }


    @Override
    public Optional<Student> update(Student entity) {
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID)) {

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setLong(3, entity.getStudentCardNumber());
            statement.setLong(4, entity.getId());

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
            log.error(SQL_ERROR);
            return Optional.empty();
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteById(long id) {
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {

            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error(SQL_ERROR);
        }
        return false;
    }

    @Override
    public List<Student> getAllStudentByCourseId(long courseId) {
        List<Student> studentList = new ArrayList<>();

        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_COURSE_ID)) {

            statement.setLong(1, courseId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                studentList.add(
                        Student.builder()
                                .id(resultSet.getLong(1))
                                .name(resultSet.getString(2))
                                .surname(resultSet.getString(3))
                                .studentCardNumber(resultSet.getLong(4))
                                .build()
                );
            }

        } catch (SQLException e) {
            log.error(SQL_ERROR);
        }

        return studentList;
    }


}
