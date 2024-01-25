package com.unboxing.ecommerce.services;

import com.unboxing.ecommerce.dto.ApiResponse;
import com.unboxing.ecommerce.entities.Task;
import com.unboxing.ecommerce.repository.TaskRepo;
import org.modelmapper.ModelMapper;
import com.unboxing.ecommerce.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TaskRepo taskRepository;

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskDto.class);
    }

    @Override
    public TaskDto markTaskAsCompleted(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(true);
            Task updatedTask = taskRepository.save(task);
            return modelMapper.map(updatedTask, TaskDto.class);
        } else {
            // Handle case where task with taskId is not found
            return null;
        }
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ApiResponse deleteTask(Long taskId) {

        ApiResponse res= new ApiResponse();

        try {
            Optional<Task> task = taskRepository.findById(taskId);
            res.setMessage("Successfully Deleted: "+task.get().getTaskId());
            res.setStatus(true);
            taskRepository.deleteById(taskId);

        }catch (Exception ex){
            res.setMessage(ex.getMessage());
            res.setStatus(false);
        }

        return res;
    }
}
