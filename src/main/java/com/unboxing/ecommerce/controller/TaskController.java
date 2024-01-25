package com.unboxing.ecommerce.controller;

import com.unboxing.ecommerce.dto.ApiResponse;
import com.unboxing.ecommerce.dto.TaskDto;
import com.unboxing.ecommerce.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {

        TaskDto createdTask = taskService.createTask(taskDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<TaskDto> markTaskAsCompleted(@PathVariable Long taskId) {
        TaskDto completedTask = taskService.markTaskAsCompleted(taskId);
        if (completedTask != null) {
            return new ResponseEntity<>(completedTask, HttpStatus.OK);
        } else {
            // Handle case where task with taskId is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Long taskId) {
        ApiResponse res=  taskService.deleteTask(taskId);
        System.out.println(res.getMessage());
        if(res.isStatus()==true){

        return new ResponseEntity<>(res,HttpStatus.NO_CONTENT);
        }
        else {

        return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
        }
    }
}