package com.bilinskiosika.organizer.domain.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long idUser;

    private String username;

    private String password;

    private String email;

    private String name;

    private String surname;

    @Column(name = "phone_number")
    private int phoneNumber;
}
