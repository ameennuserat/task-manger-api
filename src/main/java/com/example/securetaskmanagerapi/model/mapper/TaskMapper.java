package com.example.securetaskmanagerapi.model.mapper;

import com.example.securetaskmanagerapi.model.dto.requests.TaskRequestDTO;
import com.example.securetaskmanagerapi.model.dto.responses.TaskResponseDTO;
import com.example.securetaskmanagerapi.model.entity.Task;
import com.example.securetaskmanagerapi.model.enums.Status;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@Service
public class TaskMapper {

    public Task toModel(TaskRequestDTO requestDTO,String ownerId) {
        return Task.builder()
                .title(requestDTO.getTitle())
                .description(requestDTO.getDescription())
                .status(Status.PENDING)
                .dueDate(requestDTO.getDueDate())
                .ownerId(ownerId)
                .build();
    }

    public TaskResponseDTO toDTO(Task task) {
    return TaskResponseDTO.builder()
            .title(task.getTitle())
            .description(task.getDescription())
            .status(task.getStatus())
            .dueDate(task.getDueDate())
            .build();
    }

    public List<TaskResponseDTO> toDTOs(List<Task> tasks) {
        return tasks.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
