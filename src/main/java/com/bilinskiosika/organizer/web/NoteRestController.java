package com.bilinskiosika.organizer.web;

import com.bilinskiosika.organizer.domain.dto.NoteDto;
import com.bilinskiosika.organizer.domain.dto.NoteEditDto;
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
    public ResponseEntity<?> editNote(@RequestBody NoteDto noteDto) {
        if (noteService.addNote(noteDto)) {
            LOGGER.info("add note: {}", noteDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<?> addNewNote(@RequestBody NoteEditDto noteEditDto) {
        if (noteService.editNote(noteEditDto)) {
            LOGGER.info("edit note: {}", noteEditDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            LOGGER.info("note with id:" + noteEditDto.getIdNote() + " does not exist");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteById/{idNote}")
    public ResponseEntity<?> deleteNote(@PathVariable Long idNote) {
        if(noteService.deleteNote(idNote)){
            LOGGER.info("delete note: {}", idNote);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            LOGGER.info("note with id:" + idNote + " does not exist");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
