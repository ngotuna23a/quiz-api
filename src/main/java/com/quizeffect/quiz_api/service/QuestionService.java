package com.quizeffect.quiz_api.service;

import com.quizeffect.quiz_api.entity.Answer;
import com.quizeffect.quiz_api.entity.Question;
import com.quizeffect.quiz_api.repository.QuestionRepository;

import java.util.Collections;
import java.util.List;

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

    public List<Question> getRandomQuiz(Long categoryId, int numberOfQuestions) {
        // 1. Lấy tất cả câu hỏi của môn học này ra
        List<Question> questions = questionRepository.findByCategoryId(categoryId);
        
        // 2. Xáo trộn ngẫu nhiên vị trí các câu hỏi
        Collections.shuffle(questions);
        
        // 3. Giới hạn số lượng câu hỏi theo yêu cầu (ví dụ lấy 10 câu)
        if (questions.size() > numberOfQuestions) {
            questions = questions.subList(0, numberOfQuestions);
        }
        // 4. Bảo mật: Ẩn trường isCorrect của từng đáp án trước khi gửi về cho học sinh
        for (Question q : questions) {
            if (q.getAnswers() != null) {
                // Xáo trộn luôn thứ tự đáp án A, B, C, D để mỗi lần thi đề mỗi khác
                Collections.shuffle(q.getAnswers()); 
                for (Answer a : q.getAnswers()) {
                    // Tạm thời set về false hoặc null (ở mức API hiển thị) để giấu đáp án đúng
                    a.setCorrect(false); 
                }
            }
        }
        
        return questions;
    }
}