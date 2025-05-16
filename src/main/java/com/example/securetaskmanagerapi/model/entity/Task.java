package com.example.securetaskmanagerapi.model.entity;

import com.example.securetaskmanagerapi.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

//    a. id (Long, Primary Key)
//            b. title (String, Not Null)
//            c. description (String, Optional)
//            d. status (Enum: PENDING, IN_PROGRESS, COMPLETED)
//            e. dueDate (LocalDate/Timestamp, Optional)
//            f. ownerId (String, Not Null, Foreign Key referencing the Keycloak User ID)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime dueDate;

    @Column(nullable = false)
    private String ownerId;
}
