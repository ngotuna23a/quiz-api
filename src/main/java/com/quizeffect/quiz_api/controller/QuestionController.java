package com.quizeffect.quiz_api.controller;

import com.quizeffect.quiz_api.entity.Question;
import com.quizeffect.quiz_api.service.QuestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public Question create(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }
}