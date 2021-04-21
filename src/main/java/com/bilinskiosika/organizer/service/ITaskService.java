package com.bilinskiosika.organizer.service;

import com.bilinskiosika.organizer.domain.dto.TaskDetailsDto;
import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.domain.dto.TaskEditDto;

import java.text.ParseException;
import java.util.List;

public interface ITaskService {

    boolean addTask(TaskDto taskDto);

    List<TaskDto> getAllTasks(String username);

    TaskDetailsDto getTaskById(long idTask);

    TaskEditDto editTask(TaskEditDto taskEditDto, long idUser);

    boolean deleteTask(long idTask);

    List<TaskDto> getTasksBetweenDate(String firstDate, String secondDate) throws ParseException;

}
