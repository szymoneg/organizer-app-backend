package com.bilinskiosika.organizer.web;


import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.service.ITaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskRestController {

    final ITaskService taskService;

    public TaskRestController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewTask(@RequestBody TaskDto taskDto){
        if (!taskService.addTask(taskDto)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
