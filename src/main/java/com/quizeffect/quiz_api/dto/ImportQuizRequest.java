package com.quizeffect.quiz_api.dto;

import java.util.List;

public class ImportQuizRequest {
    private String name; // Tên môn học
    private String description; // Mô tả môn học
    private List<QuestionDTO> questions;

    // --- Getter và Setter ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<QuestionDTO> getQuestions() { return questions; }
    public void setQuestions(List<QuestionDTO> questions) { this.questions = questions; }

    public static class QuestionDTO {
        private String content;
        private String difficulty;
        private List<AnswerDTO> answers;

        // Getter và Setter
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }

        public String getDifficulty() { return difficulty; }
        public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

        public List<AnswerDTO> getAnswers() { return answers; }
        public void setAnswers(List<AnswerDTO> answers) { this.answers = answers; }
    }

    public static class AnswerDTO {
        private String content;
        private boolean isCorrect;

        // Getter và Setter
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }

        public boolean isCorrect() { return isCorrect; }
        public void setCorrect(boolean isCorrect) { this.isCorrect = isCorrect; }
    }
}