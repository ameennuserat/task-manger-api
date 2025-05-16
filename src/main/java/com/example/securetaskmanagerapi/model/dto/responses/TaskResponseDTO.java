package com.example.securetaskmanagerapi.model.dto.responses;

import com.example.securetaskmanagerapi.model.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime dueDate;
    private String ownerId;
}