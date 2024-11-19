package com.liyang.onlinejudgesystem.service;

import com.liyang.onlinejudgesystem.entity.QuestionSubmission;

public interface SqsProducer {
    void sendMessage(QuestionSubmission submission);
}
