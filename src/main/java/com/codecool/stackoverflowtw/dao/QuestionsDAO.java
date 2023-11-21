package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.util.List;

public interface QuestionsDAO {
    void sayHi();

    List<QuestionDTO> getAll();

    QuestionDTO getById(int questionId);
}
