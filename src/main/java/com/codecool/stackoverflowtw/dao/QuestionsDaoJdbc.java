package com.codecool.stackoverflowtw.dao;


import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.database.Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionsDaoJdbc implements QuestionsDAO {
    String userName = System.getenv("USERNAME");
    String password = System.getenv("PASSWORD");
    String dbName = System.getenv("DATABASE");
    String URL = "jdbc:postgresql://localhost:5432/" + dbName;
    Database database = new Database(URL, userName, password);

    private Connection getConnection(){
        return database.getConnection();
    }
    

    @Override
    public void sayHi() {
        System.out.println("Hi DAO!");
    }
    @Override
    public List<QuestionDTO> getAll(){
        List<QuestionDTO> questions = new ArrayList<>();

        try {
            String sql = "SELECT * FROM questions";
            Connection conn = getConnection();
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            while (resultSet.next()){
                questions.add(questionDTOBuilder(resultSet));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return questions;
    }
    @Override
    public QuestionDTO getById(int questionId){
        String sql = "select * from questions where id = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,questionId);
            ResultSet resultSet = statement.executeQuery();

                return questionDTOBuilder(resultSet);

        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addNewQuestion(NewQuestionDTO newQuestionDTO){
        int viewed = 0;
        int isAnswered = 0;
        Random random = new Random();

        String sql = "insert into questions (id, title,description, user_id,question_date,viewed,is_answered)" +
                " values(?,?,?,?,?,?,?)";
        try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, random.nextInt(99999));
            statement.setString(2, newQuestionDTO.title());
            statement.setString(3,newQuestionDTO.description());
            statement.setInt(4,newQuestionDTO.userId());
            statement.setTimestamp(5,Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(6,viewed);
            statement.setInt(7,isAnswered);
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    @Override
    public boolean deleteQuestion(int id) {
        String sql = "delete from questions where id = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }


    private QuestionDTO questionDTOBuilder(ResultSet resultSet){
        try {
            while(resultSet.next()){
                int             id = resultSet.getInt("id");
                String          title = resultSet.getString("title");
                String          description = resultSet.getString("description");
                int             userId = resultSet.getInt("user_id");
                LocalDateTime   createdAt= resultSet.getTimestamp("question_date").toLocalDateTime();
                int             viewed = resultSet.getInt("viewed");
                boolean         isAnswered = resultSet.getBoolean("is_answered");

                return new QuestionDTO(id,title,description,userId,createdAt,viewed,isAnswered);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addViewToQuestion(int id) {
        QuestionDTO currentVersion = getById(id);
        String sql = "UPDATE questions SET viewed = ? WHERE id = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentVersion.viewed() + 1);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            conn.close();
            return true;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateQuestionDescription(int id, String newDescription) {
        String sql = "UPDATE questions SET description = ? WHERE id = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newDescription);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean setAnwered(int id, boolean newBooleanValue) {
        String sql = "UPDATE questions SET isAnswered = ? WHERE id = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, newBooleanValue);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
