package com.bilinskiosika.organizer.service;

import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.domain.dto.TaskEditDto;

import java.util.List;

public interface ITaskService {

    boolean addTask(TaskDto taskDto);

    List<TaskDto> getAllTasks(String username);

    TaskEditDto editTask(TaskEditDto taskEditDto, long idUser);
}
