package com.bilinskiosika.organizer.service;

import com.bilinskiosika.organizer.domain.dto.NoteDetailsDto;
import com.bilinskiosika.organizer.domain.dto.NoteDto;
import com.bilinskiosika.organizer.domain.dto.NoteEditDto;

import java.util.List;

public interface INoteService {

    boolean addNote(NoteDto note);

    boolean editNote(NoteEditDto note);

    boolean deleteNote(long idNote);

    NoteDetailsDto getNoteById(long idNote);

    List<NoteDto> getAllNotes(String username);
}
