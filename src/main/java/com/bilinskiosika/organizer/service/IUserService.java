package com.bilinskiosika.organizer.service;

import com.bilinskiosika.organizer.domain.dto.UserDataDto;
import com.bilinskiosika.organizer.domain.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    boolean addUser(UserDto user);
    boolean editUser(UserDto user);
    UserDataDto getUser(String username);
    UserDetails loadUserByUsername(String username);
}
