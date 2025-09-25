package com.example.quiz.model;

import jakarta.persistence.*;

@Entity
@Table(name = "options")
public class Option {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    private String text;

    private Boolean isCorrect = false;

    // getters and setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public Question getQuestion(){ return question; }
    public void setQuestion(Question question){ this.question = question; }
    public String getText(){ return text; }
    public void setText(String text){ this.text = text; }
    public Boolean getIsCorrect(){ return isCorrect; }
    public void setIsCorrect(Boolean isCorrect){ this.isCorrect = isCorrect; }
}
