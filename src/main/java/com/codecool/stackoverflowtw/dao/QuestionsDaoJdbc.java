package com.codecool.stackoverflowtw.dao;


import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.database.Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private QuestionDTO questionDTOBuilder(ResultSet resultSet){
        System.out.println("hello");
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
}
