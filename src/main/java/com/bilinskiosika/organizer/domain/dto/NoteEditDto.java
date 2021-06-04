package com.bilinskiosika.organizer.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteEditDto {
    private Long idNote;
    private String titleNote;
    private String descriptionNote;
}
