package com.bilinskiosika.organizer.service;

import com.bilinskiosika.organizer.domain.dto.NoteDetailsDto;
import com.bilinskiosika.organizer.domain.dto.NoteDto;
import com.bilinskiosika.organizer.domain.dto.NoteEditDto;
import com.bilinskiosika.organizer.domain.entity.Note;

import java.util.List;

public interface INoteService {

    Note addNote(NoteDto note);

    Note editNote(NoteEditDto note);

    Note deleteNote(long idNote);

    NoteDetailsDto getNoteById(long idNote);

    List<NoteDto> getAllNotes(String username);
}
