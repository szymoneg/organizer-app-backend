package com.bilinskiosika.organizer.domain.entity;

import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.utilities.mappers.TaskDetailsDtoMapper;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public Optional<TaskDto> dto(){
        return Optional.ofNullable(TaskDto.builder()
                .titleTask(this.titleTask)
                .descriptionTask(this.descriptionTask)
                .startTask(String.valueOf(this.startTask))
                .endTask(String.valueOf(this.endTask))
                .tags(this.tags)
                .color(this.color)
                .notificationTask(String.valueOf(this.notificationTask))
                .build());
    }
}
