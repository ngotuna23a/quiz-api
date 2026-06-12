package com.quizeffect.quiz_api.service;

import com.quizeffect.quiz_api.dto.SubmitQuizRequest;
import com.quizeffect.quiz_api.entity.Answer;
import com.quizeffect.quiz_api.entity.QuizResult;
import com.quizeffect.quiz_api.repository.AnswerRepository;
import com.quizeffect.quiz_api.repository.QuizResultRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuizResultService {

    private final AnswerRepository answerRepository;
    private final QuizResultRepository quizResultRepository;

    // Tiêm các Repository thông qua Constructor (Chuẩn doanh nghiệp)
    public QuizResultService(AnswerRepository answerRepository, QuizResultRepository quizResultRepository) {
        this.answerRepository = answerRepository;
        this.quizResultRepository = quizResultRepository;
    }

    public QuizResult calculateAndSaveScore(SubmitQuizRequest request) {
        int totalQuestions = request.getAnswers().size();
        int correctCount = 0;

        for (SubmitQuizRequest.UserAnswer ua : request.getAnswers()) {
            // Tìm đáp án thật từ Database
            Answer realAnswer = answerRepository.findById(ua.getAnswerId()).orElse(null);
            
            // Đối chiếu xem đáp án có tồn tại, thuộc đúng câu hỏi và có đúng hay không
            if (realAnswer != null && realAnswer.getQuestion().getId().equals(ua.getQuestionId()) && realAnswer.isCorrect()) {
                correctCount++;
            }
        }

        // Điểm số tính theo thang điểm 10, làm tròn 2 chữ số thập phân
        double score = ((double) correctCount / totalQuestions) * 10;
        score = Math.round(score * 100.0) / 100.0;

        // Tạo bản ghi lịch sử thi mới để chuẩn bị lưu xuống DB
        QuizResult result = new QuizResult();
        result.setUserId(request.getUserId());
        result.setCategoryId(request.getCategoryId());
        result.setScore(score);
        result.setCompletedAt(LocalDateTime.now()); // Ngày giờ nộp bài hiện tại

        // Lưu trực tiếp vào bảng quiz_results trong MySQL
        return quizResultRepository.save(result);
    }
    public List<QuizResult> getResultsByUser(Long userId) {
        return quizResultRepository.findByUserId(userId);
    }
}