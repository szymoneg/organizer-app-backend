package com.bilinskiosika.organizer.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_note")
    private  long idNote;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @Column(name = "title_note")
    private String titleNote;

    @Column(name = "description_note")
    private String descriptionNote;
}
