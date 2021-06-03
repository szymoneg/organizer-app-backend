package com.bilinskiosika.organizer.domain.dto;

public class UserLoginDto {
    private Long userId;
    private String jwttoken;

    public UserLoginDto(Long userId, String jwttoken) {
        this.userId = userId;
        this.jwttoken = jwttoken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public void setJwttoken(String jwttoken) {
        this.jwttoken = jwttoken;
    }
}
