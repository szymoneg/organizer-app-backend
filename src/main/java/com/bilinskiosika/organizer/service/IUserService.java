package com.bilinskiosika.organizer.service;


import com.bilinskiosika.organizer.domain.dto.UserDto;
import com.bilinskiosika.organizer.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    User addUser(UserDto user);
    User findByUsername(UserDto user);
    UserDetails loadUserByUsername(String username);
    User findByIdUser(long idUser);
}
