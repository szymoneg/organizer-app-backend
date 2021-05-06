package com.bilinskiosika.organizer.web;


import com.bilinskiosika.organizer.domain.dto.TaskDto;
import com.bilinskiosika.organizer.domain.dto.TaskEditDto;
import com.bilinskiosika.organizer.service.ITaskService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskRestController {

    final ITaskService taskService;

    public TaskRestController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewTask(@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.addTask(taskDto), HttpStatus.OK);
    }

    @GetMapping("/getTasks/{username}")
    public ResponseEntity<?> getAllTasks(@PathVariable(name = "username") String username) {
        return new ResponseEntity<>(taskService.getAllTasks(username), HttpStatus.OK);
    }

    @GetMapping("/getTaskById/{idTask}")
    public ResponseEntity<?> getTaskById(@PathVariable(name ="idTask") long idTask){
        return new ResponseEntity<>(taskService.getTaskById(idTask), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask/{idTask}")
    public ResponseEntity<?> deleteTask(@PathVariable(name = "idTask") long idTask) {
        taskService.deleteTaskById(idTask);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/editTask/{idUser}")
    public ResponseEntity<?> editTask(@RequestBody TaskEditDto taskEditDto, @PathVariable(name = "idUser") long idUser){
        return new ResponseEntity<>(taskService.editTask(taskEditDto, idUser), HttpStatus.OK);
    }

    @GetMapping("/getTasks")
    public ResponseEntity<?> getTaskByDate(@RequestParam String firstDate, @RequestParam String secondDate) throws ParseException {
        return new ResponseEntity<>(taskService.getTasksBetweenDate(firstDate, secondDate), HttpStatus.OK);
    }
}
