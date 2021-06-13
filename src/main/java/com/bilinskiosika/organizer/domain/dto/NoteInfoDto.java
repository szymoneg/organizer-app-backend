package com.bilinskiosika.organizer.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteInfoDto {
    private Long idUser;
    private Long idNote;
    private String titleNote;
    private String descriptionNote;
}

