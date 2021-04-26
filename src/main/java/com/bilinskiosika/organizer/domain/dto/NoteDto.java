package com.bilinskiosika.organizer.domain.dto;

import lombok.Data;

@Data
public class NoteDto {
    private long idUser;
    private String titleNote;
    private String descriptionNote;
}
