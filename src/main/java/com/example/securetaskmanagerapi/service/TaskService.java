package com.example.securetaskmanagerapi.service;

import com.example.securetaskmanagerapi.model.dto.requests.TaskRequestDTO;
import com.example.securetaskmanagerapi.model.dto.requests.UpdateTaskRequestDTO;
import com.example.securetaskmanagerapi.model.dto.responses.TaskResponseDTO;

import java.util.List;

public interface TaskService {

    TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO,String ownerId)throws IllegalAccessException;
    TaskResponseDTO updateTask(UpdateTaskRequestDTO taskRequestDTO, Long taskId)throws IllegalAccessException;
    void deleteTask(Long taskId);
    TaskResponseDTO getTask(Long taskId)throws IllegalAccessException;
    List<TaskResponseDTO> getAllTasks(String ownerId);
}
