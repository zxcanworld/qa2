package com.example.qa.service.impl;

import com.example.qa.entity.Question;
import com.example.qa.repository.QuestionRepository;
import com.example.qa.service.QuestionService;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void createQuestion(Question question) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = questionRepository.getAll();
        return questionList;
    }


    public Optional<Question> getQuestionById(Long questionId) {
        return questionRepository.findById(questionId);
    }

    public Question updateQuestion(Long questionId, Question updatedQuestion) {
        Optional<Question> existingQuestion = questionRepository.findById(questionId);
        if (!existingQuestion.isEmpty()) {
            Question questionToUpdate = updatedQuestion;
            questionToUpdate.setText(updatedQuestion.getText());
            questionRepository.save(questionToUpdate);
            return questionToUpdate;
        } else {
            return null;
        }
    }

    public boolean deleteQuestion(Long questionId) {
        if (questionRepository.existsById(questionId)) {
            questionRepository.deleteById(questionId);
            return true;
        } else {
            return false;
        }
    }
}

