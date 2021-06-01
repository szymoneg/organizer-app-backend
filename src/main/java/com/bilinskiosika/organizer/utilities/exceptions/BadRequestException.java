package com.bilinskiosika.organizer.utilities.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(){
        super("Wrong parameters!");
    }
}
