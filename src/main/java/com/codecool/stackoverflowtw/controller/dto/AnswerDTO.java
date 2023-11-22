package com.codecool.stackoverflowtw.controller.dto;

import java.time.LocalDateTime;

public record AnswerDTO(int id, String description, int questionId, int userId, LocalDateTime answerDate, int score) {
}
