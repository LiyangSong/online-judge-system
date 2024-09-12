package com.liyang.onlinejudgesystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 512)
    private String title;

    private String content;

    @Column(length = 1024)
    private String tags;

    private String answer;

    @Column(nullable = false)
    private int submitNum = 0;

    @Column(nullable = false)
    private int acceptedNum = 0;

    private String judgeCase;

    private String judgeConfig;

    @Column(nullable = false)
    private int thumbNum = 0;

    @Column(nullable = false)
    private int favourNum = 0;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime = new Date();

    @Column(nullable = false)
    private Boolean isDelete = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
