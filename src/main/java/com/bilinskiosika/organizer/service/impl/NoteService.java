package com.bilinskiosika.organizer.service.impl;

import com.bilinskiosika.organizer.domain.dto.NoteDetailsDto;
import com.bilinskiosika.organizer.domain.dto.NoteDto;
import com.bilinskiosika.organizer.domain.dto.NoteEditDto;
import com.bilinskiosika.organizer.domain.dto.NoteInfoDto;
import com.bilinskiosika.organizer.domain.entity.Note;
import com.bilinskiosika.organizer.domain.entity.User;
import com.bilinskiosika.organizer.domain.repository.NoteRepository;
import com.bilinskiosika.organizer.domain.repository.UserRepository;
import com.bilinskiosika.organizer.service.INoteService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService {

    final NoteRepository noteRepository;
    final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public NoteInfoDto addNote(NoteDto noteDto) {
        Optional<User> optionalUser = userRepository.findByIdUser(noteDto.getIdUser());
        if (optionalUser.isPresent()) {
            Note newNote = Note.builder()
                    .user(optionalUser.get())
                    .titleNote(noteDto.getTitleNote())
                    .descriptionNote(noteDto.getDescriptionNote())
                    .build();
            noteRepository.save(newNote);
            return NoteInfoDto.builder()
                    .idUser(optionalUser.get().getIdUser())
                    .idNote(newNote.getIdNote())
                    .titleNote(newNote.getTitleNote())
                    .descriptionNote(newNote.getDescriptionNote())
                    .build();
        }
        return NoteInfoDto.builder()
                .build();
    }

    @Override
    public NoteEditDto editNote(NoteEditDto noteEditDto) {
        Optional<Note> optionalNote = noteRepository.findNoteByIdNote(noteEditDto.getIdNote());
        if (optionalNote.isPresent()) {
            Note newNote = optionalNote.get();
            newNote.setTitleNote(noteEditDto.getTitleNote());
            newNote.setDescriptionNote(noteEditDto.getDescriptionNote());
            noteRepository.save(newNote);
            return NoteEditDto.builder()
                    .idNote(noteEditDto.getIdNote())
                    .titleNote(noteEditDto.getTitleNote())
                    .descriptionNote(noteEditDto.getDescriptionNote())
                    .build();
        }
        return NoteEditDto.builder()
                .build();
    }

    @Override
    public void deleteNote(long idNote) {
        Optional<Note> optionalNote = noteRepository.findNoteByIdNote(idNote);
        optionalNote.ifPresent(noteRepository::delete);
    }

    @Override
    public NoteDetailsDto getNoteById(long idNote) {
        Optional<Note> optionalNote = noteRepository.findNoteByIdNote(idNote);
        if (optionalNote.isPresent()) {
            return NoteDetailsDto.builder()
                    .titleNote(optionalNote.get().getTitleNote())
                    .descriptionNote(optionalNote.get().getDescriptionNote())
                    .build();
        }
        return NoteDetailsDto.builder()
                .build();
    }

    @Override
    public List<NoteInfoDto> getAllNotes(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            List<Note> notesList = noteRepository.findNotesByUser(optionalUser.get());
            return notesList
                    .stream()
                    .map(note -> NoteInfoDto.builder()
                            .idUser(optionalUser.get().getIdUser())
                            .idNote(note.getIdNote())
                            .titleNote(note.getTitleNote())
                            .descriptionNote(note.getDescriptionNote())
                            .build()
                    ).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
