package com.liyang.onlinejudgesystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    ADMIN("Administrator"),
    USER("User");

    private final String description;

    public static UserRole fromString(String userRole) {
        for (UserRole role : UserRole.values()) {
            if (role.name().equalsIgnoreCase(userRole)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid UserRole: " + userRole);
    }
}
