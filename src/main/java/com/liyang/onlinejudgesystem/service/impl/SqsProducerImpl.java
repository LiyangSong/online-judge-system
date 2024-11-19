package com.liyang.onlinejudgesystem.service.impl;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liyang.onlinejudgesystem.dto.Message;
import com.liyang.onlinejudgesystem.entity.QuestionSubmission;
import com.liyang.onlinejudgesystem.service.SqsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SqsProducerImpl implements SqsProducer {
    @Value("${aws.queue.url}")
    private String queueUrl;

    private final AmazonSQS amazonSQS;
    private final ObjectMapper objectMapper;

    @Autowired
    public SqsProducerImpl(AmazonSQS amazonSQS, ObjectMapper objectMapper) {
        this.amazonSQS = amazonSQS;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(QuestionSubmission submission) {
        Message message = new Message(
                submission.getId(),
                submission.getQuestion().getId(),
                submission.getLanguage(),
                submission.getCode(),
                submission.getJudgeInfo(),
                new Date()
        );

        try {
            String messageBody = objectMapper.writeValueAsString(message);
            SendMessageRequest sendMsgRequest = new SendMessageRequest()
                    .withQueueUrl(queueUrl)
                    .withMessageBody(messageBody);
            amazonSQS.sendMessage(sendMsgRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize and send message", e);
        }
    }
}
