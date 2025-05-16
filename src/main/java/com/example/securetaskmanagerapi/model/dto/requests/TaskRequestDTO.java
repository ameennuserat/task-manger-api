package com.example.securetaskmanagerapi.model.dto.requests;

import com.example.securetaskmanagerapi.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskRequestDTO {
    @NotBlank
    private String title;
    private String description;
    private Status status;
    private LocalDateTime dueDate;
}