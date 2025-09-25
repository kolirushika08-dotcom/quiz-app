package com.example.quiz.controller;

import com.example.quiz.model.Option;
import com.example.quiz.model.Question;
import com.example.quiz.repository.OptionRepository;
import com.example.quiz.repository.QuestionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    public AdminController(QuestionRepository questionRepository, OptionRepository optionRepository){
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
    }

    @GetMapping("/questions")
    public List<Question> list(){ return questionRepository.findAll(); }

    @PostMapping("/questions")
    public Question createQuestion(@RequestBody Question q){
        // Ensure options link back to question
        if(q.getOptions()!=null){
            for(Option o: q.getOptions()){
                o.setQuestion(q);
            }
        }
        return questionRepository.save(q);
    }

    @DeleteMapping("/questions/{id}")
    public void delete(@PathVariable Long id){ questionRepository.deleteById(id); }
}
