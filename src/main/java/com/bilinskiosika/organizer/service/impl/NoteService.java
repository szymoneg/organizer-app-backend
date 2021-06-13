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
import com.bilinskiosika.organizer.utilities.exceptions.NoteNotFoundException;
import com.bilinskiosika.organizer.utilities.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

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

    /**
     * <p>This is a description of the add note method</p>
     * @param noteDto object represents note
     * @return note data
     * @throws UserNotFoundException username not found
     */
    @Override
    public NoteInfoDto addNote(NoteDto noteDto) {
        User user = userRepository
                .findByIdUser(noteDto.getIdUser())
                .orElseThrow(() -> new UserNotFoundException("username"));

        Note newNote = Note.builder()
                .user(user)
                .titleNote(noteDto.getTitleNote())
                .descriptionNote(noteDto.getDescriptionNote())
                .build();
        noteRepository.save(newNote);

        return NoteInfoDto.builder()
                .idUser(user.getIdUser())
                .idNote(newNote.getIdNote())
                .titleNote(newNote.getTitleNote())
                .descriptionNote(newNote.getDescriptionNote())
                .build();

    }

    /**
     * <p>This is a description of the edit note method</p>
     * @param noteEditDto object represents data necessary to edit a note
     * @return note data
     * @throws NoteNotFoundException note not found
     */
    @Override
    public NoteEditDto editNote(NoteEditDto noteEditDto) {
        Note note = noteRepository
                .findNoteByIdNote(noteEditDto.getIdNote())
                .orElseThrow(() -> new NoteNotFoundException(noteEditDto.getIdNote()));

        note.setTitleNote(noteEditDto.getTitleNote());
        note.setDescriptionNote(noteEditDto.getDescriptionNote());

        noteRepository.save(note);

        return NoteEditDto.builder()
                .idNote(noteEditDto.getIdNote())
                .titleNote(noteEditDto.getTitleNote())
                .descriptionNote(noteEditDto.getDescriptionNote())
                .build();

    }

    /**
     * <p>This is a description of the delete note method</p>
     * @param idNote id note to delete note
     */
    @Override
    public void deleteNote(long idNote) {
        Optional<Note> optionalNote = noteRepository.findNoteByIdNote(idNote);
        optionalNote.ifPresent(noteRepository::delete);
    }

    /**
     * <p>This is a description of the get single note by id method</p>
     * @param idNote id note to get note data
     * @return note data find by id note
     * @throws NoteNotFoundException note not found
     */
    @Override
    public NoteDetailsDto getNoteById(long idNote) {
        Note note = noteRepository
                .findNoteByIdNote(idNote)
                .orElseThrow(() -> new NoteNotFoundException(idNote));
        return NoteDetailsDto.builder()
                .titleNote(note.getTitleNote())
                .descriptionNote(note.getDescriptionNote())
                .build();
    }

    /**
     * <p>This is a description of the return all user note method</p>
     * @param username id note to get note data
     * @return all user note
     * @throws UserNotFoundException username not found
     */
    @Override
    public List<NoteInfoDto> getAllNotes(String username) {
        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        List<Note> notesList = noteRepository.findNotesByUser(user);
        return notesList
                .stream()
                .map(note -> NoteInfoDto.builder()
                        .idUser(user.getIdUser())
                        .idNote(note.getIdNote())
                        .titleNote(note.getTitleNote())
                        .descriptionNote(note.getDescriptionNote())
                        .build()
                ).collect(Collectors.toList());
    }
}
