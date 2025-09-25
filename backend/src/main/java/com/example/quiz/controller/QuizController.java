package com.example.quiz.controller;

import com.example.quiz.model.Option;
import com.example.quiz.model.Question;
import com.example.quiz.model.Result;
import com.example.quiz.repository.OptionRepository;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.repository.ResultRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*")
public class QuizController {
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final ResultRepository resultRepository;

    public QuizController(QuestionRepository questionRepository, OptionRepository optionRepository, ResultRepository resultRepository){
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
        this.resultRepository = resultRepository;
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    @PostMapping("/submit")
    public Map<String,Object> submitQuiz(@RequestBody Map<String, Object> payload){
        // payload expected: { "answers": { "questionId": optionId, ... }, "userId": 1 }
        Map<String,Integer> answers = (Map<String,Integer>) payload.get("answers");
        Integer userId = payload.get("userId") == null ? null : (Integer) payload.get("userId");
        int total = 0;
        for(String qidStr : answers.keySet()){
            Long qid = Long.parseLong(qidStr);
            Optional<Question> qOpt = questionRepository.findById(qid);
            if(qOpt.isPresent()){
                Long chosenOptionId = answers.get(qidStr).longValue();
                Optional<Option> opt = optionRepository.findById(chosenOptionId);
                if(opt.isPresent() && Boolean.TRUE.equals(opt.get().getIsCorrect())){
                    total += qOpt.get().getScore();
                }
            }
        }
        Result r = new Result();
        r.setUserId(userId == null ? null : userId.longValue());
        r.setTotalScore(total);
        resultRepository.save(r);
        Map<String,Object> res = new HashMap<>();
        res.put("score", total);
        res.put("resultId", r.getId());
        return res;
    }

    @GetMapping("/results")
    public List<Result> getResults(){ return resultRepository.findAll(); }
}
