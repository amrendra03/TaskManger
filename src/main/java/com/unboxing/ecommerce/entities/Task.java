package com.unboxing.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    private String title;

    private String description;

    private boolean completed;
}