package com.quizeffect.quiz_api.repository;

import com.quizeffect.quiz_api.entity.Question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategoryId(Long categoryId);
}