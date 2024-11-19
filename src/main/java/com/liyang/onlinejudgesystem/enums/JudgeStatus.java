package com.liyang.onlinejudgesystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JudgeStatus {
    WAITING("Waiting"),
    IN_PROGRESS("In Progress"),
    ACCEPTED("Accepted"),
    WRONG_ANSWER("Wrong Answer"),
    COMPILE_ERROR("Compile Error"),
    MEMORY_LIMIT_EXCEEDED("Memory Limit Exceeded"),
    TIME_LIMIT_EXCEEDED("Time Limit Exceeded"),
    PRESENTATION_ERROR("Presentation Error"),
    OUTPUT_LIMIT_EXCEEDED("Output Limit Exceeded"),
    DANGEROUS_OPERATION("Dangerous Operation"),
    RUNTIME_ERROR("Runtime Error"),
    SYSTEM_ERROR("System Error");

    private final String description;
}
