package com.quizeffect.quiz_api.repository;

import com.quizeffect.quiz_api.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}