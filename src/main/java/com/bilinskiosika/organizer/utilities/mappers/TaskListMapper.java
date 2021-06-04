package com.bilinskiosika.organizer.utilities.mappers;

import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.domain.entity.Task;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TaskListMapper implements Converter<List<TaskDto>, List<Task>> {

    @Override
    public List<TaskDto> convert(List<Task> from) {
        return from.stream().map(tasks -> {
            return TaskDto.builder()
                    .idTask(tasks.getIdTask())
                    .titleTask(tasks.getTitleTask())
                    .color(tasks.getColor())
                    .descriptionTask(tasks.getDescriptionTask())
                    .startTask(tasks.getStartTask().toString())
                    .endTask(tasks.getEndTask().toString())
                    .notificationTask(tasks.getNotificationTask().toString())
                    .tags(tasks.getTags())
                    .build();
        }).collect(Collectors.toList());
    }
}
