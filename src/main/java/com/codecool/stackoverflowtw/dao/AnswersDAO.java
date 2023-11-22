package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;

import java.sql.ResultSet;
import java.util.List;

public interface AnswersDAO {

    void sayHi();

    List<AnswerDTO> getAllByQuestionId(int questionId);

    AnswerDTO getById(int answerId);

    boolean deleteAnswer(int answerId);

    boolean updateAnswerDescription(int answerId, String newDescription);

    boolean updateScore(int answerId, int newScore);

    boolean createAnswer(NewAnswerDTO answerDTO);

    AnswerDTO answerDTOBuilder(ResultSet resultSet);
}
