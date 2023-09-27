package com.example.qa.service.impl;
import com.example.qa.entity.Answer;
import com.example.qa.repository.AnswerRepository;
import com.example.qa.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Optional<Answer> getAnswerById(Long answerId) {
        return answerRepository.findById(answerId);
    }

    public Answer updateAnswer(Long answerId, Answer updatedAnswer) {
        Optional<Answer> existingAnswer = answerRepository.findById(answerId);
        if (existingAnswer.isPresent()) {
            updatedAnswer.setId(answerId);
            return answerRepository.save(updatedAnswer);
        } else {
            return null;
        }
    }

    public boolean deleteAnswer(Long answerId) {
        Optional<Answer> existingAnswer = answerRepository.findById(answerId);
        if (existingAnswer.isPresent()) {
            answerRepository.deleteById(answerId);
            return true;
        } else {
            return false;
        }
    }

}