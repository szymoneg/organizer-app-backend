package com.bilinskiosika.organizer.web;

import com.bilinskiosika.organizer.domain.dto.NoteDto;
import com.bilinskiosika.organizer.domain.dto.NoteEditDto;
import com.bilinskiosika.organizer.domain.dto.NoteInfoDto;
import com.bilinskiosika.organizer.domain.entity.Note;
import com.bilinskiosika.organizer.service.INoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/note")
public class NoteRestController {

    final INoteService noteService;
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteRestController.class);

    public NoteRestController(INoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewNote(@RequestBody NoteDto noteDto) {
        NoteInfoDto newNote = noteService.addNote(noteDto);
        LOGGER.info("add note: {}", newNote);
        return new ResponseEntity<>(newNote, HttpStatus.CREATED);
    }
    @GetMapping("/getById/{idNote}")
    public ResponseEntity<?> getNoteById(@PathVariable Long idNote) {
        return new ResponseEntity<>(noteService.getNoteById(idNote), HttpStatus.OK);
    }

    @GetMapping("/getAll/{username}")
    public ResponseEntity<?> getAllNotes(@PathVariable String username) {
        return new ResponseEntity<>(noteService.getAllNotes(username), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editNote(@RequestBody NoteEditDto noteEditDto) {
        NoteEditDto newNote = noteService.editNote(noteEditDto);
        LOGGER.info("edit note: {}", newNote);
        return new ResponseEntity<>(newNote, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{idNote}")
    public ResponseEntity<?> deleteNote(@PathVariable Long idNote) {
        noteService.deleteNote(idNote);
            LOGGER.info("delete note: {}", idNote);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
