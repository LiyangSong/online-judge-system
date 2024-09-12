package com.liyang.onlinejudgesystem.repository;

import com.liyang.onlinejudgesystem.entity.QuestionSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionSubmissionRepository extends JpaRepository<QuestionSubmission, Long> {
}
