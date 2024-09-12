package com.liyang.onlinejudgesystem.repository;

import com.liyang.onlinejudgesystem.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
