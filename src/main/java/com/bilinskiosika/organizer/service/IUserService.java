package com.bilinskiosika.organizer.service;

import com.bilinskiosika.organizer.domain.dto.UserDetailsDto;
import com.bilinskiosika.organizer.domain.dto.UserDto;
import com.bilinskiosika.organizer.domain.dto.UserEditDto;
import com.bilinskiosika.organizer.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    User addUser(UserDto user);
    User editUser(UserEditDto user);
    UserDetailsDto getUser(String username);
    UserDetails loadUserByUsername(String username);
}
