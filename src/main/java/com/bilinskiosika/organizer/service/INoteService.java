package com.bilinskiosika.organizer.service;

import com.bilinskiosika.organizer.domain.dto.NoteDetailsDto;
import com.bilinskiosika.organizer.domain.dto.NoteDto;
import com.bilinskiosika.organizer.domain.dto.NoteEditDto;
import com.bilinskiosika.organizer.domain.dto.NoteInfoDto;
import com.bilinskiosika.organizer.domain.entity.Note;

import java.util.List;

public interface INoteService {

    NoteInfoDto addNote(NoteDto note);

    NoteEditDto editNote(NoteEditDto note);

    void deleteNote(long idNote);

    NoteDetailsDto getNoteById(long idNote);

    List<NoteInfoDto> getAllNotes(String username);
}
