package com.bilinskiosika.organizer.domain.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TaskDto {
    private long idUser;
    private String titleTask;
    private String descriptionTask;
    private String duration;
    private String tags;
    private String color;
    private Date notificationTask;
}
