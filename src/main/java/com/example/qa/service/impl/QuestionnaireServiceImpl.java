package com.example.qa.service.impl;

import com.example.qa.entity.Questionnaire;
import com.example.qa.repository.QuestionnaireRepository;
import com.example.qa.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;

    @Autowired
    public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }

    public Questionnaire createQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    public List<Questionnaire> getAllQuestionnaires() {
        return questionnaireRepository.findAll();
    }

    public Questionnaire updateQuestionnaire(Long questionnaireId, Questionnaire updatedQuestionnaire) {
        Questionnaire existingQuestionnaire = questionnaireRepository.findById(questionnaireId).orElse(null);
        if (existingQuestionnaire != null) {
            existingQuestionnaire.setName(updatedQuestionnaire.getName());
            existingQuestionnaire.setType(updatedQuestionnaire.getType());
            return questionnaireRepository.save(existingQuestionnaire);
        } else {
            return null;
        }
    }

    public boolean deleteQuestionnaire(Long questionnaireId) {
        if (questionnaireRepository.existsById(questionnaireId)) {
            questionnaireRepository.deleteById(questionnaireId);
            return true;
        } else {
            return false;
        }
    }
}
