package com.bilinskiosika.organizer.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_task")
    private long idTask;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "title_task")
    private String titleTask;

    @Column(name = "description_task")
    private String descriptionTask;

    private String duration;

    private String tags;

    private String color;

    @Column(name = "notification_task")
    private Date notificationTask;

}
