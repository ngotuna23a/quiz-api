package com.quizeffect.quiz_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; 

    @Column(nullable = false)
    private boolean isCorrect; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean isCorrect) { this.isCorrect = isCorrect; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }
}