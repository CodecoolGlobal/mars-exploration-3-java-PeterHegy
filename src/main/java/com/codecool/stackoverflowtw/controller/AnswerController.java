package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.service.AnswerService;
import com.codecool.stackoverflowtw.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/all/{id}")
    public List<AnswerDTO> getAllAnswersById(@PathVariable int id) {
        return answerService.getAllByQuestionId(id);
    }

    @GetMapping("/{id}")
    public AnswerDTO getQuestionById(@PathVariable int id) {
        return answerService.getAnswerById(id);
    }

    @PostMapping("/")
    public int addNewQuestion(@RequestBody NewAnswerDTO answer) {
        if(answerService.addNewAnswer(answer)) {
            return 200;
        }
        return 300;
    }

    @DeleteMapping("/{id}")
    public boolean deleteQuestionById(@PathVariable int id) {
        return answerService.deleteAnswerById(id);
    }

}
