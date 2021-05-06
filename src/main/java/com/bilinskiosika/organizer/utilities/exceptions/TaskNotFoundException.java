package com.bilinskiosika.organizer.utilities.exceptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(long id){
        super("Could not find task: " + id);
    }
}
