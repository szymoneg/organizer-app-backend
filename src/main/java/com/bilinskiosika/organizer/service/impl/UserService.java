package com.bilinskiosika.organizer.service.impl;

import com.bilinskiosika.organizer.domain.dto.UserDetailsDto;
import com.bilinskiosika.organizer.domain.dto.UserDto;
import com.bilinskiosika.organizer.domain.dto.UserEditDto;
import com.bilinskiosika.organizer.domain.entity.User;
import com.bilinskiosika.organizer.domain.repository.UserRepository;
import com.bilinskiosika.organizer.service.IUserService;
import com.bilinskiosika.organizer.utilities.exceptions.NoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.Collections;
import java.util.List;


@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * <p>This is a description of the add new user method</p>
     * @param user object represents data necessary to create new user
     * @return created user
     */
    @Override
    public User addUser(UserDto user) {
        User newUser = new User();
        try {
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setEmail(user.getEmail());
            newUser.setName(user.getName());
            newUser.setSurname(user.getSurname());
            newUser.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(newUser);
            return newUser;
        } catch (Exception e) {
            //TODO error body
            return new User();
        }
    }

    /**
     * <p>This is a description of the get user by username method</p>
     * @param username value need to find user in database
     * @return found user or empty object
     */
    @Override
    public UserDetailsDto getUser(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            UserDetailsDto newUser = new UserDetailsDto();
            newUser.setUsername(optionalUser.get().getUsername());
            newUser.setEmail(optionalUser.get().getEmail());
            newUser.setName(optionalUser.get().getName());
            newUser.setSurname(optionalUser.get().getSurname());
            newUser.setPhoneNumber(optionalUser.get().getPhoneNumber());
            return newUser;
        }
        return new UserDetailsDto();
    }

    /**
     * <p>This is a description of the edit user method</p>
     * @param user object represents data necessary to edit user
     * @return edited user
     */
    @Override
    public User editUser(UserEditDto user) {

        Optional<User> optionalUser = userRepository.findUserByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            User newUser = optionalUser.get();
            newUser.setName(user.getName());
            newUser.setSurname(user.getSurname());
            newUser.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(newUser);
            return newUser;
        }

        return new User();

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;

        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ENABLE"));
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), roles);
        }
        throw new UsernameNotFoundException("User not found with the name " + username);
    }

    @Override
    public User findByIdUser(long idUser) {
        return userRepository.findByIdUser(idUser).get();
    }

    @Override
    public Long getUserId(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return user.getIdUser();
    }
}
