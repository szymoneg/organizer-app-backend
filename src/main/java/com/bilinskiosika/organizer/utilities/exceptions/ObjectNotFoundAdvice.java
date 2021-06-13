package com.bilinskiosika.organizer.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ObjectNotFoundAdvice {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String taskNotFoundHandler(TaskNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String usernameNotFoundHandler(UserNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badRequestHandler(BadRequestException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String noteNotFoundHandler(NoteNotFoundException exception) {
        return exception.getMessage();
    }

}
