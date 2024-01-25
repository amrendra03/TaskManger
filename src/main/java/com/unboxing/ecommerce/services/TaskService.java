package com.unboxing.ecommerce.services;

import com.unboxing.ecommerce.dto.ApiResponse;
import com.unboxing.ecommerce.dto.TaskDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {

    TaskDto createTask(TaskDto taskDTO);

    TaskDto markTaskAsCompleted(Long taskId);

    List<TaskDto> getAllTasks();

    ApiResponse deleteTask(Long taskId);
}

