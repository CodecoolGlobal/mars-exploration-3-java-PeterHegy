package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;

    }

    public List<QuestionDTO> getAllQuestions() {

        return questionsDAO.getAll();
    }

    public QuestionDTO getQuestionById(int id) {
        return questionsDAO.getById(id);
    }

    public boolean deleteQuestionById(int id) {
        // TODO
        return false;
    }

    public boolean addNewQuestion(NewQuestionDTO question) {

        return questionsDAO.addNewQuestion(question);
    }
}
