package com.adv.quizapp.controller;

import com.adv.quizapp.model.Question;
import com.adv.quizapp.model.QuestionWrapper;
import com.adv.quizapp.model.Response;
import com.adv.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQestions(@PathVariable int id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuestions(@PathVariable int id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }
}
