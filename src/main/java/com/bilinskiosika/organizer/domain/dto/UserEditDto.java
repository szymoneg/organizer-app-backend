package com.bilinskiosika.organizer.domain.dto;

import lombok.Data;

@Data
public class UserEditDto {
    private String username;
    private String name;
    private String surname;
    private int phoneNumber;

    UserEditDto(){}
}
