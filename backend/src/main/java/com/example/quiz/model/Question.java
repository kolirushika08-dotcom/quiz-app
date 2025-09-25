package com.example.quiz.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Integer score = 1;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options;

    // getters and setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getText(){ return text; }
    public void setText(String text){ this.text = text; }
    public Integer getScore(){ return score; }
    public void setScore(Integer score){ this.score = score; }
    public List<Option> getOptions(){ return options; }
    public void setOptions(List<Option> options){ this.options = options; }
}
