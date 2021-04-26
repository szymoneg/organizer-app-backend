package com.bilinskiosika.organizer.domain.dto;

import lombok.Data;

@Data
public class NoteEditDto {
    private long idNote;
    private String titleNote;
    private String descriptionNote;
}
