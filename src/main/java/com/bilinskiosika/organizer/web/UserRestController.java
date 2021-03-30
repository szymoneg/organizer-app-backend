package com.bilinskiosika.organizer.web;

import com.bilinskiosika.organizer.config.JwtTokenUtil;
import com.bilinskiosika.organizer.domain.dto.UserDto;
import com.bilinskiosika.organizer.domain.model.JwtRequest;
import com.bilinskiosika.organizer.domain.model.JwtResponse;
import com.bilinskiosika.organizer.service.IUserService;
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

    @Autowired
    public UserRestController(IUserService iUserService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userService = iUserService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @RequestMapping({"/hello"})
    public String firstPage() {
        return "Hello World";
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody UserDto user) {
        if (userService.findByUsername(user) == null) {
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        } else
            return new ResponseEntity<>("username: " + user.getUsername() + "is already taken", HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/showUserData/{idUser}")
    public ResponseEntity<?> showUser(@PathVariable(name = "idUser") long idUser){
        return new ResponseEntity<>(userService.findByIdUser(idUser), HttpStatus.OK);
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
