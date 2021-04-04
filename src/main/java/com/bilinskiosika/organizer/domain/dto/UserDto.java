package com.bilinskiosika.organizer.domain.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private int phoneNumber;
}