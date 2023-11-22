package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.AnswersDaoJdbc;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StackoverflowTwApplication {
    public static void main(String[] args) {
        SpringApplication.run(StackoverflowTwApplication.class, args);
    }
    @Bean
    public QuestionsDAO questionsDAO() {
        return new QuestionsDaoJdbc();
    }

    @Bean
    public AnswersDAO answersDAO() {
        return new AnswersDaoJdbc();
    }

}
