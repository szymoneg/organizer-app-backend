package com.bilinskiosika.organizer.utilities.exceptions;

public class NoteNotFoundException extends RuntimeException{
    public NoteNotFoundException(long id){
        super("Could not find note: " + id);
    }
}
