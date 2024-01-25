package com.unboxing.ecommerce.repository;

import com.unboxing.ecommerce.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task,Long> {
}
