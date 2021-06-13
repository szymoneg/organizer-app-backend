package com.bilinskiosika.organizer.domain.repository;

import com.bilinskiosika.organizer.domain.dto.NoteDto;
import com.bilinskiosika.organizer.domain.entity.Note;
import com.bilinskiosika.organizer.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long>, CrudRepository<Note, Long> {

    Optional<Note> findNoteByIdNote(long idUser);

    List<Note> findNotesByUser(User user);
}
