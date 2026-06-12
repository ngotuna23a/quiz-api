package com.quizeffect.quiz_api.dto;

import java.util.List;

public class SubmitQuizRequest {
    private Long userId;
    private Long categoryId;
    private List<UserAnswer> answers;

    // --- Getter và Setter ---
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public List<UserAnswer> getAnswers() { return answers; }
    public void setAnswers(List<UserAnswer> answers) { this.answers = answers; }

    // Lớp con (Lưu cặp Câu hỏi - Đáp án đã chọn)
    public static class UserAnswer {
        private Long questionId;
        private Long answerId;

        public Long getQuestionId() { return questionId; }
        public void setQuestionId(Long questionId) { this.questionId = questionId; }

        public Long getAnswerId() { return answerId; }
        public void setAnswerId(Long answerId) { this.answerId = answerId; }
    }
}