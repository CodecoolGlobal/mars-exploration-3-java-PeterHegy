package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnswerService {


    private AnswersDAO answersDAO;

    @Autowired
    public AnswerService(AnswersDAO answersDAO) {
        this.answersDAO = answersDAO;

    }

    public List<AnswerDTO> getAllByQuestionId(int questionId) {
        // TODO
        return answersDAO.getAllByQuestionId(questionId);
    }

    public
    AnswerDTO getAnswerById(int id) {
        // TODO
        return answersDAO.getById(id);
    }

    public boolean deleteAnswerById(int id) {
        // TODO
        return answersDAO.deleteAnswer(id);
    }

    public boolean addNewAnswer(NewAnswerDTO answerDTO) {
        // TODO
        LocalDateTime createdId = LocalDateTime.now();
        System.out.println("Answer created at: " + createdId);
        return answersDAO.createAnswer(answerDTO);
    }

}
