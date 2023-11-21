package com.codecool.stackoverflowtw.controller.dto;

import java.time.LocalDateTime;

public record QuestionDTO(int id, String title, String description, int userId,LocalDateTime created, int viewed,boolean isAnswered) {}
