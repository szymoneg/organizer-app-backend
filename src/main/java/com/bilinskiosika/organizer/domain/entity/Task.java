package com.bilinskiosika.organizer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_task")
    private long idTask;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @Column(name = "title_task")
    private String titleTask;

    @Column(name = "description_task")
    private String descriptionTask;

    @Column(name = "start_task")
    private Timestamp startTask;

    @Column(name = "end_task")
    private Timestamp endTask;

    private String tags;

    private String color;

    @Column(name = "notification_task")
    private Timestamp notificationTask;

}
