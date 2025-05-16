package com.example.securetaskmanagerapi.model.dto.responses;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@Builder
public class ExceptionResponse {
    private Date timestamp;
    private int statusCode;
    private String error;
    private String message;
    private String path;
}
