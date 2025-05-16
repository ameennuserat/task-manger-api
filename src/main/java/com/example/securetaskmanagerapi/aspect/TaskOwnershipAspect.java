package com.example.securetaskmanagerapi.aspect;


import com.example.securetaskmanagerapi.Repositories.TaskRepository;
import com.example.securetaskmanagerapi.exception.TaskNotFoundException;
import com.example.securetaskmanagerapi.exception.UnauthorizedTaskAccessException;
import com.example.securetaskmanagerapi.model.entity.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.aspectj.lang.reflect.MethodSignature;
import java.lang.reflect.Parameter;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class TaskOwnershipAspect {

    private final TaskRepository taskRepository;

    @Before("@annotation(CheckTaskOwnership)")
    public void checkOwnership(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();

        Long taskId = null;
        Jwt jwt = null;

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getType().equals(Long.class)) {
                taskId = (Long) args[i];
            }
            if (parameters[i].getType().equals(Jwt.class)) {
                jwt = (Jwt) args[i];
            }
        }

        if (taskId == null || jwt == null) {
            throw new IllegalArgumentException("Task ID or JWT not provided to method");
        }

        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isEmpty()) {
            throw new TaskNotFoundException();
        }

        Task task = taskOptional.get();
        String userId = jwt.getSubject();
        if (!task.getOwnerId().equals(userId)) {
            throw new UnauthorizedTaskAccessException("You do not own this task");
        }
    }
}
