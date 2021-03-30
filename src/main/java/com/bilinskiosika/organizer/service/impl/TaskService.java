package com.bilinskiosika.organizer.service.impl;

import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.domain.entity.Task;
import com.bilinskiosika.organizer.domain.repository.TaskRepository;
import com.bilinskiosika.organizer.domain.repository.UserRepository;
import com.bilinskiosika.organizer.service.ITaskService;
import com.vladmihalcea.hibernate.type.range.Range;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

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
        try {
            Task newTask = new Task();
            newTask.setUser(userRepository.findByIdUser(taskDto.getIdUser()));
            newTask.setTitleTask(taskDto.getTitleTask());
            newTask.setDescriptionTask(taskDto.getDescriptionTask());
            newTask.setStartTask(Timestamp.valueOf(taskDto.getStartTask()));
            newTask.setEndTask(Timestamp.valueOf(taskDto.getEndTask()));
            newTask.setTags(taskDto.getTags());
            newTask.setColor(taskDto.getColor());
            newTask.setNotificationTask(Timestamp.valueOf(taskDto.getNotificationTask()));

            taskRepository.save(newTask);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
