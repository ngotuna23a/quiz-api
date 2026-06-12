package com.quizeffect.quiz_api.controller;

import com.quizeffect.quiz_api.dto.SubmitQuizRequest;
import com.quizeffect.quiz_api.entity.QuizResult;
import com.quizeffect.quiz_api.service.QuizResultService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizResultController {

    private final QuizResultService quizResultService;

    public QuizResultController(QuizResultService quizResultService) {
        this.quizResultService = quizResultService;
    }

    // API Nộp bài: http://localhost:8080/api/quiz/submit
    @PostMapping("/submit")
    public QuizResult submit(@RequestBody SubmitQuizRequest request) {
        return quizResultService.calculateAndSaveScore(request);
    }
}