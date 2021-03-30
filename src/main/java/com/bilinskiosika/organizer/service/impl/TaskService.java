package com.bilinskiosika.organizer.service.impl;

import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.domain.entity.Task;
import com.bilinskiosika.organizer.domain.repository.TaskRepository;
import com.bilinskiosika.organizer.domain.repository.UserRepository;
import com.bilinskiosika.organizer.service.ITaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService {

    final TaskRepository taskRepository;
    final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    @Override
    public boolean addTask(TaskDto taskDto) {
        Task newTask = new Task();
        newTask.setUser(userRepository.findByIdUser(taskDto.getIdUser()));
        newTask.setTitleTask(taskDto.getTitleTask());
        newTask.setDescriptionTask(taskDto.getDescriptionTask());
        newTask.setDuration(taskDto.getDuration());
        newTask.setTags(taskDto.getTags());
        newTask.setColor(taskDto.getColor());
        newTask.setNotificationTask(taskDto.getNotificationTask());

        taskRepository.save(newTask);

        return false;
    }
}
