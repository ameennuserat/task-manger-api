package com.example.securetaskmanagerapi.controller;


import com.example.securetaskmanagerapi.aspect.CheckTaskOwnership;
import com.example.securetaskmanagerapi.model.dto.requests.TaskRequestDTO;
import com.example.securetaskmanagerapi.model.dto.requests.UpdateTaskRequestDTO;
import com.example.securetaskmanagerapi.model.dto.responses.TaskResponseDTO;
import com.example.securetaskmanagerapi.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.persister.entity.mutation.UpdateCoordinator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@RequestBody @Valid TaskRequestDTO dto,
                                                  @AuthenticationPrincipal Jwt jwt) throws IllegalAccessException {
        String ownerId = jwt.getSubject();
        return ResponseEntity.status(201).body(taskService.createTask(dto, ownerId));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAll(@AuthenticationPrincipal Jwt jwt) {
        String ownerId = jwt.getSubject();
        return ResponseEntity.ok(taskService.getAllTasks(ownerId));
    }

    @CheckTaskOwnership
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getById(@PathVariable Long id,
                                                   @AuthenticationPrincipal Jwt jwt) throws IllegalAccessException {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @CheckTaskOwnership
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(@PathVariable Long id,
                                                  @RequestBody  UpdateTaskRequestDTO dto,
                                                  @AuthenticationPrincipal Jwt jwt) throws IllegalAccessException {
        return ResponseEntity.ok(taskService.updateTask(dto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @AuthenticationPrincipal Jwt jwt) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}