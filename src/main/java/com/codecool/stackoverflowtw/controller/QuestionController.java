package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public QuestionDTO getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping("/")
    public boolean addNewQuestion(@RequestBody NewQuestionDTO questionDTO) {
        return questionService.addNewQuestion(questionDTO);
    }

    @PatchMapping("/update/view/{id}")
    public boolean addOneViewToQuestion(@PathVariable int id) {return questionService.addOneViewToQuestion(id);}

    @PatchMapping("/update/description/{id}")
    public boolean updateQuestionDescription(@PathVariable int id, @RequestBody String newDescription) {
        return questionService.updateQuestionDescription(id, newDescription);
    }
    @PatchMapping("/update/isanswered/{id}")
    public boolean updateIsAnswered(@PathVariable int id, @RequestBody boolean newBooleanValue) {
        return questionService.updateQuestionIsAnswered(id, newBooleanValue);
    }

    @DeleteMapping("/{id}")
    public boolean deleteQuestionById(@PathVariable int id) {
        return questionService.deleteQuestionById(id);
    }



}
