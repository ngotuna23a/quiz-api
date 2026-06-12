package com.quizeffect.quiz_api.repository;

import com.quizeffect.quiz_api.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    // Tìm kiếm lịch sử thi theo ID của người dùng
    List<QuizResult> findByUserId(Long userId);
}