package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.util.List;

public interface QuestionsDAO {
    void sayHi();

    List<QuestionDTO> getAll();

    QuestionDTO getById(int questionId);

    int addNewQuestion(NewQuestionDTO newQuestionDTO);

    boolean addViewToQuestion(int id);

    boolean deleteQuestion(int id);
}
