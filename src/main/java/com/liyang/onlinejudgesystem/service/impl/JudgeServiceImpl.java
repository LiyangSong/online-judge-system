package com.liyang.onlinejudgesystem.service.impl;

import com.liyang.onlinejudgesystem.entity.QuestionSubmission;
import com.liyang.onlinejudgesystem.entity.Question;
import com.liyang.onlinejudgesystem.enums.JudgeStatus;
import com.liyang.onlinejudgesystem.repository.QuestionRepository;
import com.liyang.onlinejudgesystem.repository.QuestionSubmissionRepository;
import com.liyang.onlinejudgesystem.service.JudgeService;
import com.liyang.onlinejudgesystem.service.SqsProducer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class JudgeServiceImpl implements JudgeService {
    QuestionRepository questionRepository;
    QuestionSubmissionRepository questionSubmissionRepository;
    SqsProducer sqsProducer;

    @Transactional
    public QuestionSubmission doJudge(Long questionSubmissionId) {
        // Retrieve and validate question submission
        QuestionSubmission questionSubmission = questionSubmissionRepository.findById(questionSubmissionId)
                .orElseThrow(() -> new ObjectNotFoundException(questionSubmissionId, "QuestionSubmission"));

        if (questionSubmission.getIsDeleted()) {
            throw new IllegalStateException("QuestionSubmission has been deleted");
        }

        if (!questionSubmission.getStatus().equals(JudgeStatus.WAITING)) {
            throw new IllegalStateException("QuestionSubmission status is not WAITING");
        }

        // Retrieve and validate question
        Question question = questionSubmission.getQuestion();

        if (question.getIsDelete()) {
            throw new IllegalStateException("Question has been deleted");
        }

        // Update submission in DB
        questionSubmission.setStatus(JudgeStatus.IN_PROGRESS);
        questionSubmission.setUpdateTime(new Date());
        QuestionSubmission savedSubmission = questionSubmissionRepository.save(questionSubmission);

        // Build and send message to SQS
        sqsProducer.sendMessage(savedSubmission);

        // Update question in DB
        question.setSubmitNum(question.getSubmitNum() + 1);
        questionRepository.save(question);

        return savedSubmission;
    }
}
