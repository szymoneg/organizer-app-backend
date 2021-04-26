package com.bilinskiosika.organizer.service.impl;

import com.bilinskiosika.organizer.domain.dto.NoteDetailsDto;
import com.bilinskiosika.organizer.domain.dto.NoteDto;
import com.bilinskiosika.organizer.domain.dto.NoteEditDto;
import com.bilinskiosika.organizer.domain.entity.Note;
import com.bilinskiosika.organizer.domain.repository.NoteRepository;
import com.bilinskiosika.organizer.domain.repository.UserRepository;
import com.bilinskiosika.organizer.service.INoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements INoteService {

    final NoteRepository noteRepository;
    final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean addNote(NoteDto noteDto) {
        Note newNote = new Note();
        try {
            newNote.setUser(userRepository.findUserByIdUser(noteDto.getIdUser()));
            newNote.setTitleNote(noteDto.getTitleNote());
            newNote.setDescriptionNote(noteDto.getDescriptionNote());
            noteRepository.save(newNote);
            return true;
        } catch (Exception e) {
            //TODO error body
            return false;
        }
    }

    @Override
    public boolean editNote(NoteEditDto noteEditDto) {
        try {
            Note newNote = noteRepository.findNoteByIdNote(noteEditDto.getIdNote());
            newNote.setTitleNote(noteEditDto.getTitleNote());
            newNote.setDescriptionNote(noteEditDto.getDescriptionNote());
            noteRepository.save(newNote);
            return true;
        } catch (Exception e) {
            //TODO error body
            return false;
        }
    }

    @Override
    public boolean deleteNote(long idNote) {
        try {
            Note removedNote = noteRepository.findNoteByIdNote(idNote);
            noteRepository.delete(removedNote);
            return true;
        } catch (Exception e) {
            //TODO error body
            return false;
        }
    }

    @Override
    public NoteDetailsDto getNoteById(long idNote) {
        //TODO mappers
        Note note = noteRepository.findNoteByIdNote(idNote);
        NoteDetailsDto noteDetailsDto = new NoteDetailsDto();
        noteDetailsDto.setTitleNote(note.getTitleNote());
        noteDetailsDto.setDescriptionNote(note.getDescriptionNote());
        return noteDetailsDto;
    }

    @Override
    public List<NoteDto> getAllNotes(String username) {

        return null;
    }
}
