package com.bilinskiosika.organizer.service.impl;

import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.domain.dto.TaskEditDto;
import com.bilinskiosika.organizer.domain.entity.Task;
import com.bilinskiosika.organizer.domain.entity.User;
import com.bilinskiosika.organizer.domain.repository.TaskRepository;
import com.bilinskiosika.organizer.domain.repository.UserRepository;
import com.bilinskiosika.organizer.service.ITaskService;
import com.bilinskiosika.organizer.utilities.mappers.TaskMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TaskService implements ITaskService {

    final TaskRepository taskRepository;
    final UserRepository userRepository;
    final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
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

    @Override
    public List<TaskDto> getAllTasks(String username) {
        User user = userRepository.findByUsername(username);
        return taskMapper.taskToDtoMapper(taskRepository.findTaskByUser(user));
    }

    @Override
    public TaskEditDto editTask(TaskEditDto taskEditDto, long idUser) {
        return null;
    }
}
