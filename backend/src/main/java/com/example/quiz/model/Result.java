package com.example.quiz.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "results")
public class Result {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Integer totalScore;

    private LocalDateTime takenAt;

    @PrePersist
    public void prePersist(){ this.takenAt = LocalDateTime.now(); }

    // getters/setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public Long getUserId(){ return userId; }
    public void setUserId(Long userId){ this.userId = userId; }
    public Integer getTotalScore(){ return totalScore; }
    public void setTotalScore(Integer totalScore){ this.totalScore = totalScore; }
    public LocalDateTime getTakenAt(){ return takenAt; }
    public void setTakenAt(LocalDateTime takenAt){ this.takenAt = takenAt; }
}
