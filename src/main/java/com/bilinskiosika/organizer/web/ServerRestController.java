package com.bilinskiosika.organizer.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ServerRestController {

    @GetMapping("/server")
    public ResponseEntity<String> serverTest(){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
