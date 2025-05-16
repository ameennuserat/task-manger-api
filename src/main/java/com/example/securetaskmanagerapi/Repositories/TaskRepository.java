package com.example.securetaskmanagerapi.Repositories;

import com.example.securetaskmanagerapi.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByOwnerId(String ownerId);
    Optional<Task> findByIdAndOwnerId(Long id, String ownerId);
}