package com.bilinskiosika.organizer.service;

import com.bilinskiosika.organizer.domain.dto.TaskDetailsDto;
import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.domain.dto.TaskEditDto;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface ITaskService {

    Optional<TaskDto> addTask(TaskDto taskDto);

    List<TaskDto> getAllTasks(String username);

    TaskDetailsDto getTaskById(long idTask);

    Optional<TaskDto> editTask(TaskEditDto taskEditDto, long idUser);

    void deleteTaskById(long idTask);

    List<TaskDto> getTasksBetweenDate(String firstDate, String secondDate) throws ParseException;

}
