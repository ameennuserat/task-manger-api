package com.example.securetaskmanagerapi.service.implement;

import com.example.securetaskmanagerapi.Repositories.TaskRepository;
import com.example.securetaskmanagerapi.model.dto.requests.TaskRequestDTO;
import com.example.securetaskmanagerapi.model.dto.requests.UpdateTaskRequestDTO;
import com.example.securetaskmanagerapi.model.dto.responses.TaskResponseDTO;
import com.example.securetaskmanagerapi.model.entity.Task;
import com.example.securetaskmanagerapi.model.mapper.TaskMapper;
import com.example.securetaskmanagerapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO,String ownerId) throws IllegalAccessException {
        Task task = taskMapper.toModel(taskRequestDTO, ownerId);
        try {
            taskRepository.save(task);
        }catch (Exception e){
            throw new IllegalAccessException(e.getLocalizedMessage());
        }
        return taskMapper.toDTO(task);
    }

    @Override
    public TaskResponseDTO updateTask(UpdateTaskRequestDTO taskRequestDTO, Long id) throws IllegalAccessException {
        Task task = taskRepository.findById(id).orElseThrow(()->new IllegalAccessException("Task not found"));
       return taskMapper.toDTO(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        taskRepository.delete(task);
    }

    @Override
    public TaskResponseDTO getTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        return taskMapper.toDTO(task);
    }

    @Override
    public List<TaskResponseDTO> getAllTasks(String ownerId) {
        List<Task> tasks = taskRepository.findByOwnerId(ownerId);
        return taskMapper.toDTOs(tasks);
    }
}
