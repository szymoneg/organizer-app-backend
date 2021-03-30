package com.bilinskiosika.organizer.service;

import com.bilinskiosika.organizer.domain.dto.TaskDto;

public interface ITaskService {
    boolean addTask(TaskDto taskDto);
}
