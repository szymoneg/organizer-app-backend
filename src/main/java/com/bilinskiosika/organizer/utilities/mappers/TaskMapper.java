package com.bilinskiosika.organizer.utilities.mappers;

import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.domain.entity.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskMapper {

    public List<TaskDto> taskToDtoMapper(List<Task> tasks) {
        List<TaskDto> listTask = new ArrayList<>();

        for (Task task : tasks) {
            TaskDto taskDto = TaskDto.builder()
                    .idUser(task.getIdTask())
                    .titleTask(task.getTitleTask())
                    .color(task.getColor())
                    .descriptionTask(task.getDescriptionTask())
                    .startTask(task.getStartTask().toString())
                    .endTask(task.getEndTask().toString())
                    .notificationTask(task.getNotificationTask().toString())
                    .tags(task.getTags())
                    .build();

            listTask.add(taskDto);
        }

        return listTask;
    }

}
