package com.bilinskiosika.organizer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class NoteDto {
    private Long idUser;
    private String titleNote;
    private String descriptionNote;
}
