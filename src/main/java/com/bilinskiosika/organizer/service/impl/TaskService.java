package com.bilinskiosika.organizer.service.impl;

import com.bilinskiosika.organizer.domain.dto.TaskDetailsDto;
import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.domain.dto.TaskEditDto;
import com.bilinskiosika.organizer.service.ITaskService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class TaskService implements ITaskService {
    @Override
    public boolean addTask(TaskDto taskDto) {


        return false;
    }

    @Override
    public List<TaskDto> getAllTasks(String username) {
        return null;
    }

    @Override
    public TaskDetailsDto getTaskById(long idTask) {
        return null;
    }

    @Override
    public TaskEditDto editTask(TaskEditDto taskEditDto, long idUser) {
        return null;
    }

    @Override
    public boolean deleteTask(long idTask) {
        return false;
    }

    @Override
    public List<TaskDto> getTasksBetweenDate(String firstDate, String secondDate) throws ParseException {
        return null;
    }


//    @Override
//    public List<TaskDto> getTasksBetweenDate(String firstDate, String secondDate) {
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//            List<Task> findTasks = taskRepository.findAllTasksByDate(formatter.parse(firstDate), formatter.parse(secondDate));
//            if (findTasks.isEmpty()) {
//                return Collections.emptyList();
//            } else {
//                return taskMapper.taskToDtoMapper(findTasks);
//            }
//        }catch (ParseException ex){
//            ex.printStackTrace();
//            return Collections.emptyList();
//        }
//    }
}
