package com.adv.quizapp.service;

import com.adv.quizapp.model.Question;
import com.adv.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to add question :(", HttpStatus.BAD_REQUEST);
    }

    public String updateQuestion(int id, Question question) {
        Question oldQuestion = questionDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));

        oldQuestion.setQuestionTitle(question.getQuestionTitle());
        oldQuestion.setOption1(question.getOption1());
        oldQuestion.setOption2(question.getOption2());
        oldQuestion.setOption3(question.getOption3());
        oldQuestion.setOption4(question.getOption4());
        oldQuestion.setDifficultyLevel(question.getDifficultyLevel());
        oldQuestion.setCategory(question.getCategory());
        oldQuestion.setRightAnswer(question.getRightAnswer());
        questionDao.save(oldQuestion);
        return "Updated!";
    }

    public String deleteById(int id) {
        questionDao.deleteById(id);
        return "Deleted!";
    }
}
