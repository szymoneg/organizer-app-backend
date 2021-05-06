package com.bilinskiosika.organizer.service.impl;

import com.bilinskiosika.organizer.domain.dto.TaskDetailsDto;
import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.domain.dto.TaskEditDto;
import com.bilinskiosika.organizer.domain.entity.Task;
import com.bilinskiosika.organizer.domain.entity.User;
import com.bilinskiosika.organizer.domain.repository.TaskRepository;
import com.bilinskiosika.organizer.domain.repository.UserRepository;
import com.bilinskiosika.organizer.service.ITaskService;
import com.bilinskiosika.organizer.utilities.exceptions.BadRequestException;
import com.bilinskiosika.organizer.utilities.exceptions.TaskNotFoundException;
import com.bilinskiosika.organizer.utilities.exceptions.UserNotFoundException;
import com.bilinskiosika.organizer.utilities.mappers.TaskDetailsDtoMapper;
import com.bilinskiosika.organizer.utilities.mappers.TaskListMapper;
import com.bilinskiosika.organizer.utilities.mappers.TaskMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {
    final TaskRepository taskRepository;
    final UserRepository userRepository;
    final TaskMapper taskMapper;

    TaskDetailsDtoMapper taskDetailsDtoMapper = new TaskDetailsDtoMapper();
    TaskListMapper taskListMapper = new TaskListMapper();

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public Optional<TaskDto> addTask(TaskDto taskDto) {
        //TODO Opcjonalnie zrobić zebezpieczenie przed próbą dodania przez nieistniejącego użytkownika
        User user = Optional.of(userRepository
                .findByIdUser(taskDto.getIdUser()))
                .orElseThrow(() -> new UserNotFoundException("username"));

        Task newTask = taskMapper.convert(taskDto).orElseThrow(BadRequestException::new);
        taskRepository.save(newTask);
        return newTask.dto();
    }

    @Override
    public List<TaskDto> getAllTasks(String username) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return taskListMapper
                .convert(taskRepository
                        .findTaskByUser(user));
    }

    @Override
    public TaskDetailsDto getTaskById(long idTask) {
        return taskDetailsDtoMapper
                .convert(taskRepository
                        .findTaskByIdTask(idTask)
                        .orElseThrow(() -> new TaskNotFoundException(idTask)));
    }

    @Override
    public Optional<TaskDto> editTask(TaskEditDto taskEditDto, long idUser) {
        Task oldTask = taskRepository
                .findTaskByIdTask(taskEditDto.getIdTask())
                .orElseThrow(() -> new TaskNotFoundException(taskEditDto.getIdTask()));

        oldTask.setTitleTask(taskEditDto.getTitleTask());
        oldTask.setColor(taskEditDto.getColor());
        oldTask.setTags(taskEditDto.getTags());
        oldTask.setDescriptionTask(taskEditDto.getDescriptionTask());
        oldTask.setStartTask(Timestamp.valueOf(taskEditDto.getStartTask()));
        oldTask.setEndTask(Timestamp.valueOf(taskEditDto.getEndTask()));
        oldTask.setNotificationTask(Timestamp.valueOf(taskEditDto.getNotificationTask()));

        taskRepository.save(oldTask);

        return oldTask.dto();
    }

    @Override
    public void deleteTaskById(long idTask) {
        Task taskToDelete = taskRepository
                .findTaskByIdTask(idTask)
                .orElseThrow(() -> new TaskNotFoundException(idTask));

        taskRepository.delete(taskToDelete);
    }

    @Override
    public List<TaskDto> getTasksBetweenDate(String firstDate, String secondDate) throws ParseException {
        return null;
    }
}
