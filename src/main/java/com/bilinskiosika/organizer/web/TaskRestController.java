package com.bilinskiosika.organizer.web;


import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.service.ITaskService;
import com.bilinskiosika.organizer.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskRestController {

    final ITaskService taskService;

    public TaskRestController(ITaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> addNewTask(TaskDto taskDto){
        taskService.addTask(taskDto);

        return new ResponseEntity<>("lel", HttpStatus.OK);
    }
}
