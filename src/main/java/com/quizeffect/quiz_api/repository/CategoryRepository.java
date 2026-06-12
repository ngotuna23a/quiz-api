package com.quizeffect.quiz_api.repository;

import com.quizeffect.quiz_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}