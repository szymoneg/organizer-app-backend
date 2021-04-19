package com.bilinskiosika.organizer.service.impl;

import com.bilinskiosika.organizer.domain.dto.TaskDetailsDto;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            System.out.println("Username doesn't exist!");
            return Collections.emptyList();
        }else {
            return taskMapper.taskToDtoMapper(taskRepository.findTaskByUser(user.get()));
        }
    }

    @Override
    public TaskEditDto editTask(TaskEditDto taskEditDto, long idUser) {
        Optional<Task> oldTask = taskRepository.findTaskByIdTask(taskEditDto.getIdTask());
        if(oldTask.isPresent()){
            //TODO napewno da się zrobić lepiej?
            oldTask.get().setColor(taskEditDto.getColor());
            oldTask.get().setTags(taskEditDto.getTags());
            oldTask.get().setDescriptionTask(taskEditDto.getDescriptionTask());
            oldTask.get().setTitleTask(taskEditDto.getTitleTask());
            oldTask.get().setStartTask(Timestamp.valueOf(taskEditDto.getStartTask()));
            oldTask.get().setEndTask(Timestamp.valueOf(taskEditDto.getEndTask()));
            oldTask.get().setNotificationTask(Timestamp.valueOf(taskEditDto.getNotificationTask()));

            return new TaskEditDto();
        }
        return new TaskEditDto();
    }

    @Override
    public TaskDetailsDto getTaskById(long idTask) {
        TaskDetailsDto taskDetailsDto = taskMapper.taskToDetailsDto(taskRepository.findTaskByIdTask(idTask));
        return taskDetailsDto;
    }

    @Override
    public boolean deleteTask(long idTask) {
        Optional<Task> removedTask = taskRepository.findTaskByIdTask(idTask);
        if(removedTask.isEmpty()){
            System.out.println("Task doesn't exist");
            return false;
        }
        taskRepository.delete(removedTask.get());
        return true;
    }

    @Override
    public List<TaskDetailsDto> getTasksBetweenDate(String firstDate, String secondDate) {
        return null;
    }
}
