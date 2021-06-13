package com.bilinskiosika.organizer.utilities.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username){
        super("Could not find: " + username);
    }
}
