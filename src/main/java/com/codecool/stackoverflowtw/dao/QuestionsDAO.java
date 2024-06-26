package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.util.List;

public interface QuestionsDAO {
    void sayHi();

    List<QuestionDTO> getAll();

    QuestionDTO getById(int questionId);

    boolean addNewQuestion(NewQuestionDTO newQuestionDTO);
    boolean deleteQuestion(int id);
    boolean addViewToQuestion(int id);
    boolean updateQuestionDescription(int id, String description);
    boolean setAnwered(int id, boolean newBooleanValue);

    List<QuestionDTO> getQueriedQuestions(String filter, String sort);
}
