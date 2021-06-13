package com.bilinskiosika.organizer.domain.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TaskDto {
    private long idTask;
    private long idUser;
    private String titleTask;
    private String descriptionTask;
    private String startTask;
    private String endTask;
    private String tags;
    private String color;
    private String notificationTask;
}
