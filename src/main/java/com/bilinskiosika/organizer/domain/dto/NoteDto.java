package com.bilinskiosika.organizer.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteDto {
    private Long idUser;
    private String titleNote;
    private String descriptionNote;
}
