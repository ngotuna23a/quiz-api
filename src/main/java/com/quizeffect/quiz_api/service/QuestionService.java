package com.quizeffect.quiz_api.service;

import com.quizeffect.quiz_api.entity.Answer;
import com.quizeffect.quiz_api.entity.Question;
import com.quizeffect.quiz_api.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Transactional // Đảm bảo tính toàn vẹn dữ liệu
    public Question createQuestion(Question question) {
        // Bắt buộc phải gán mối quan hệ ngược: Từng đáp án phải biết nó thuộc về câu hỏi nào
        if (question.getAnswers() != null) {
            for (Answer answer : question.getAnswers()) {
                answer.setQuestion(question);
            }
        }
        return questionRepository.save(question);
    }
}