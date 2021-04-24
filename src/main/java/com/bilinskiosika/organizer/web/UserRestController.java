package com.bilinskiosika.organizer.web;

import com.bilinskiosika.organizer.config.JwtTokenUtil;
import com.bilinskiosika.organizer.domain.dto.UserDataDto;
import com.bilinskiosika.organizer.domain.dto.UserDto;
import com.bilinskiosika.organizer.domain.model.JwtRequest;
import com.bilinskiosika.organizer.domain.model.JwtResponse;
import com.bilinskiosika.organizer.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserRestController {

    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);


    @Autowired
    public UserRestController(IUserService iUserService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userService = iUserService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody UserDto user) {
        if (userService.addUser(user)) {
            LOGGER.info("add user: {}", user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            LOGGER.info("username already taken: {}", user.getUsername());
        return new ResponseEntity<>("username: " + user.getUsername() + " is already taken", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/data")
    public ResponseEntity<?> getUser(@RequestParam String username) {
        UserDataDto user = userService.getUser(username);
        if (user.getUsername() != null) {
            LOGGER.info("get user: {}", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            LOGGER.info("Username not found: {}", username);
            return new ResponseEntity<>("User with username: " + username + " does not exist", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody UserDto user) {
        if (userService.editUser(user)) {
            LOGGER.info("edit user: {}", user.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            LOGGER.info("Username not found: {}", user.getUsername());
        return new ResponseEntity<>("User with username: " + user.getUsername() + " does not exist", HttpStatus.BAD_REQUEST);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
