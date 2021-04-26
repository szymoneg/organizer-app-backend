package com.bilinskiosika.organizer.domain.dto;


import lombok.Data;

@Data
public class UserDataDto {
    private String username;
    private String email;
    private String name;
    private String surname;
    private int phoneNumber;

    public UserDataDto(){}
}
