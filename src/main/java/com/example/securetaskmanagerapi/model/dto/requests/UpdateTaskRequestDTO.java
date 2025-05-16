package com.example.securetaskmanagerapi.model.dto.requests;

import com.example.securetaskmanagerapi.model.enums.Status;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateTaskRequestDTO {
    private String title;
    private String description;
    private Status status;
}