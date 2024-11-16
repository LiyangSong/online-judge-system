package com.liyang.onlinejudgesystem.entity;

import com.liyang.onlinejudgesystem.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 256)
    private String username;

    @Column(nullable = false, length = 512)
    private String userPassword;

    @Column(length = 1024)
    private String userAvatar;

    @Column(length = 512)
    private String userProfile;

    @Column(nullable = false, length = 256)
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime = new Date();

    @Column(nullable = false)
    private Boolean isDelete = false;
}
