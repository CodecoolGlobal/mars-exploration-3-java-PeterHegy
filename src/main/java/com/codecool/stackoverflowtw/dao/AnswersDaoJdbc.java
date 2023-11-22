package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.database.Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnswersDaoJdbc implements AnswersDAO{

    String userName = System.getenv("USERNAME");
    String password = System.getenv("PASSWORD");
    String dbName = System.getenv("DATABASE");
    String URL = "jdbc:postgresql://localhost:5432/" + dbName;
    Database database = new Database(URL, userName, password);

    @Override
    public void sayHi() {
        System.out.println("Hello answersDAO!");
    }

    @Override
    public List<AnswerDTO> getAllByQuestionId(int questionId) {
        List<AnswerDTO> answers = new ArrayList<>();
        String sql = "SELECT * FROM answers WHERE question_id = ?";

        try(Connection conn = database.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, questionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                answers.add(answerDTOBuilder(resultSet));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return answers;
    }

    @Override
    public AnswerDTO getById(int answerId) {
        AnswerDTO answer = null;

        String sql = "SELECT * FROM answers WHERE id = ?";

        try(Connection conn = database.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, answerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                answer = answerDTOBuilder(resultSet);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return answer;
    }

    @Override
    public boolean deleteAnswer(int answerId) {
        String sql = "DELETE FROM answers WHERE id = ?";

        try (Connection conn = database.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, answerId);
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean updateAnswerDescription(int answerId, String newDescription) {
        String sql = "UPDATE answers SET description = ? WHERE id = ?";

        try (Connection conn = database.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, newDescription);
            preparedStatement.setInt(2, answerId);
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean updateScore(int answerId, int newScore){
        String sql = "UPDATE answers SET score = ? WHERE id = ?";

        try (Connection conn = database.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, newScore);
            preparedStatement.setInt(2, answerId);
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;

    }

    @Override
    public boolean createAnswer(NewAnswerDTO answerDTO) {
        String sql = "INSERT INTO answers(description, question_id, user_id, answer_date, score) " +
                "values(?, ?, ?, ?, ?)";


        try (Connection conn = database.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            int initScore = 0;

            preparedStatement.setString(1, answerDTO.description());
            preparedStatement.setInt(2, answerDTO.questionId());
            preparedStatement.setInt(3, answerDTO.userId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setInt(5, initScore);

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public AnswerDTO answerDTOBuilder(ResultSet resultSet) {
        try {

            int answerId = resultSet.getInt("id");
            String desc = resultSet.getString("description");
            int answersQuestionId = resultSet.getInt("question_id");
            int userId = resultSet.getInt("user_id");
            LocalDateTime answerDate = resultSet.getTimestamp("answer_date").toLocalDateTime();
            int score = resultSet.getInt("score");

            System.out.println(answerId + desc + answersQuestionId + userId + answerDate + score);

            return new AnswerDTO(answerId, desc, answersQuestionId, userId, answerDate, score);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
