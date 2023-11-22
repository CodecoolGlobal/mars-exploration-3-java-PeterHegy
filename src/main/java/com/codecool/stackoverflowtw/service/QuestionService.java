package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return questionsDAO.deleteQuestion(id);
    }

    public int addNewQuestion(NewQuestionDTO question) {
        // TODO
        questionsDAO.addNewQuestion(question);
        return 0;
    }
    public boolean addOneViewToQuestion(int id) {
        return questionsDAO.addViewToQuestion(id);
    }

    public boolean updateQuestionDescription(int id, String newDescription) {
        return questionsDAO.updateQuestionDescription(id, newDescription);
    }
}
