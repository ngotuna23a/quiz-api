package com.quizeffect.quiz_api.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "categories") 
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false, length = 100) 
    private String name;

    @Column(columnDefinition = "TEXT") 
    private String description;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;
    public List<Question> getQuestions() { return questions;}
    public void setQuestions(List<Question> questions) { this.questions = questions; }
}