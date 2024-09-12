package com.liyang.onlinejudgesystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 256)
    private String userAccount;

    @Column(nullable = false, length = 512)
    private String userPassword;

    @Column(length = 256)
    private String userName;

    @Column(length = 1024)
    private String userAvatar;

    @Column(length = 512)
    private String userProfile;

    @Column(nullable = false, length = 256)
    private String userRole = "user";

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime = new Date();

    @Column(nullable = false)
    private Boolean isDelete = false;
}
