package com.quizeffect.quiz_api.repository;

import com.quizeffect.quiz_api.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}