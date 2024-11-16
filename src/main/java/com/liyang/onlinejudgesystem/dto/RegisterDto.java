package com.liyang.onlinejudgesystem.dto;

public record RegisterDto(
    String username,
    String userPassword,
    String userRole  //"ADMIN" or "USER"
) {}
