package com.example.qa.controller;

import com.example.qa.entity.Answer;
import com.example.qa.service.impl.AnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerServiceImpl answerService;

    @PostMapping
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer) {
        Answer createdAnswer = answerService.createAnswer(answer);
        return ResponseEntity.ok(createdAnswer);
    }

    @GetMapping
    public ResponseEntity<List<Answer>> getAllAnswers() {
        List<Answer> answers = answerService.getAllAnswers();
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/{answerId}")
    public ResponseEntity<?> getAnswerById(@PathVariable Long answerId) {
        Optional<Answer> answer = answerService.getAnswerById(answerId);
        if (answer != null) {
            return ResponseEntity.ok(answer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{answerId}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long answerId, @RequestBody Answer answer) {
        Answer updatedAnswer = answerService.updateAnswer(answerId, answer);
        if (updatedAnswer != null) {
            return ResponseEntity.ok(updatedAnswer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
        boolean deleted = answerService.deleteAnswer(answerId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}