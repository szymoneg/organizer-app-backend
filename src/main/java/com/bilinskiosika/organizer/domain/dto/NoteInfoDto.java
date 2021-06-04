package com.bilinskiosika.organizer.domain.dto;

import lombok.Data;

@Data
public class NoteInfoDto {
    private Long idUser;
    private Long idNote;
    private String titleNote;
    private String descriptionNote;

    public NoteInfoDto(Long idUser, Long idNote, String titleNote, String descriptionNote) {
        this.idUser = idUser;
        this.idNote = idNote;
        this.titleNote = titleNote;
        this.descriptionNote = descriptionNote;
    }
    public NoteInfoDto(){}
}

