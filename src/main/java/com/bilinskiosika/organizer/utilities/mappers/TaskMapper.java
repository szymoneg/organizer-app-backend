package com.bilinskiosika.organizer.utilities.mappers;

import com.bilinskiosika.organizer.domain.dto.TaskDetailsDto;
import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.domain.entity.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public TaskDetailsDto taskToDetailsDto(Optional<Task> task){
        try {
            return TaskDetailsDto.builder()
                    .idTask(task.get().getIdTask())
                    .titleTask(task.get().getTitleTask())
                    .descriptionTask(task.get().getDescriptionTask())
                    .startTask(task.get().getStartTask().toString())
                    .endTask(task.get().getEndTask().toString())
                    .color(task.get().getColor())
                    .tags(task.get().getTags())
                    .notificationTask(task.get().getNotificationTask().toString())
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return new TaskDetailsDto();
        }
    }

}
