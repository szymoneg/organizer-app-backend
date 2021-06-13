package com.bilinskiosika.organizer.domain.dto;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private int phoneNumber;

    public UserDto(){}
}
