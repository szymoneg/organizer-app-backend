package com.bilinskiosika.organizer.service.impl;

import com.bilinskiosika.organizer.domain.dto.UserDataDto;
import com.bilinskiosika.organizer.domain.dto.UserDto;
import com.bilinskiosika.organizer.domain.entity.User;
import com.bilinskiosika.organizer.domain.repository.UserRepository;
import com.bilinskiosika.organizer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //TODO mappers
    @Override
    public boolean addUser(UserDto user) {
        User newUser = new User();
        try {
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setEmail(user.getEmail());
            newUser.setName(user.getName());
            newUser.setSurname(user.getSurname());
            newUser.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(newUser);
            return true;
        } catch (Exception e) {
            //TODO error body
            return false;
        }
    }

    @Override
    public UserDataDto getUser(String username) {
        try {
            User user = userRepository.findUserByUsername(username);
            UserDataDto newUser = new UserDataDto();
            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());
            newUser.setName(user.getName());
            newUser.setSurname(user.getSurname());
            newUser.setPhoneNumber(user.getPhoneNumber());
            return newUser;
        } catch (Exception e) {
            //TODO error body
            return new UserDataDto();
        }
    }

    @Override
    public boolean editUser(UserDto user) {
        try {
            User newUser = userRepository.findUserByUsername(user.getUsername());
            newUser.setEmail(user.getEmail());
            newUser.setName(user.getName());
            newUser.setSurname(user.getSurname());
            newUser.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(newUser);
            return true;
        } catch (Exception e) {
            //TODO error body
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}
