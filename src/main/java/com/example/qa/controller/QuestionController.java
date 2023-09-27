package com.example.qa.controller;

import com.example.qa.entity.Question;
import com.example.qa.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionServiceImpl questionService;

    @Autowired
    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        questionService.createQuestion(question);
        return ResponseEntity.ok(question);
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long questionId) {
        Optional<Question> question = questionService.getQuestionById(questionId);
        if (question.isPresent()) {
            return ResponseEntity.ok(question.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long questionId, @RequestBody Question updatedQuestion) {
        Question question = questionService.updateQuestion(questionId, updatedQuestion);
        if (question != null) {
            return ResponseEntity.ok(question);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        boolean deleted = questionService.deleteQuestion(questionId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
