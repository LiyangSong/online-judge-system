package com.liyang.onlinejudgesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Long questionSubmissionId;
    private Long questionId;
    private String language;
    private String code;
    private String judgeInfo;
    private Date createTime;
}
