package com.bilinskiosika.organizer.utilities.mappers;

import com.bilinskiosika.organizer.domain.dto.TaskDetailsDto;
import com.bilinskiosika.organizer.domain.entity.Task;

import java.sql.Timestamp;

public class TaskDetailsDtoMapper implements Converter<TaskDetailsDto, Task>{
    @Override
    public TaskDetailsDto convert(Task from) {
        return TaskDetailsDto.builder()
                .titleTask(from.getTitleTask())
                .descriptionTask(from.getDescriptionTask())
                .startTask(String.valueOf(from.getStartTask()))
                .endTask(String.valueOf(from.getEndTask()))
                .tags(from.getTags())
                .color(from.getColor())
                .notificationTask(String.valueOf(from.getNotificationTask()))
                .build();
    }
}
