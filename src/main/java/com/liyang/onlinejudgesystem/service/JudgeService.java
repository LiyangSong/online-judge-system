package com.liyang.onlinejudgesystem.service;

import com.liyang.onlinejudgesystem.entity.QuestionSubmission;

public interface JudgeService {
    QuestionSubmission doJudge(Long questionSubmissionId);
}
