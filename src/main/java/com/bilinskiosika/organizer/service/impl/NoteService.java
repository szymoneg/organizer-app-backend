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
        Note newNote = new Note();
        Optional<User> optionalUser = userRepository.findByIdUser(noteDto.getIdUser());
        if (optionalUser.isPresent()) {
            newNote.setUser(optionalUser.get());
            newNote.setTitleNote(noteDto.getTitleNote());
            newNote.setDescriptionNote(noteDto.getDescriptionNote());
            noteRepository.save(newNote);
            return new NoteInfoDto(
                    noteDto.getIdUser(),
                    newNote.getIdNote(),
                    noteDto.getTitleNote(),
                    noteDto.getDescriptionNote());
        }
        return new NoteInfoDto();
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
            NoteDetailsDto noteDetailsDto = new NoteDetailsDto();
            noteDetailsDto.setTitleNote(optionalNote.get().getTitleNote());
            noteDetailsDto.setDescriptionNote(optionalNote.get().getDescriptionNote());
            return noteDetailsDto;
        }
        return new NoteDetailsDto();
    }

    @Override
    public List<NoteInfoDto> getAllNotes(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            List<Note> notesList = noteRepository.findNotesByUser(optionalUser.get());
            return notesList
                    .stream()
                    .map(note -> new NoteInfoDto(
                            optionalUser.get().getIdUser(),
                            note.getIdNote(),
                            note.getTitleNote(),
                            note.getDescriptionNote())
                    ).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
