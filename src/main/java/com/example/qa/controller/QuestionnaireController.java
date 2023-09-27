package com.example.qa.controller;

import com.example.qa.entity.Questionnaire;
import com.example.qa.service.impl.QuestionnaireServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questionnaires")
public class QuestionnaireController {

    private final QuestionnaireServiceImpl questionnaireService;

    @Autowired
    public QuestionnaireController(QuestionnaireServiceImpl questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @PostMapping
    public ResponseEntity<Questionnaire> createQuestionnaire(@RequestBody Questionnaire questionnaire) {
        Questionnaire createdQuestionnaire = questionnaireService.createQuestionnaire(questionnaire);
        return ResponseEntity.ok(createdQuestionnaire);
    }

    @GetMapping
    public ResponseEntity<List<Questionnaire>> getAllQuestionnaires() {
        List<Questionnaire> questionnaires = questionnaireService.getAllQuestionnaires();
        return ResponseEntity.ok(questionnaires);
    }

    @PutMapping("/{questionnaireId}")
    public ResponseEntity<Questionnaire> updateQuestionnaire(@PathVariable Long questionnaireId, @RequestBody Questionnaire updatedQuestionnaire) {
        Questionnaire questionnaire = questionnaireService.updateQuestionnaire(questionnaireId, updatedQuestionnaire);
        if (questionnaire != null) {
            return ResponseEntity.ok(questionnaire);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{questionnaireId}")
    public ResponseEntity<Void> deleteQuestionnaire(@PathVariable Long questionnaireId) {
        boolean deleted = questionnaireService.deleteQuestionnaire(questionnaireId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}