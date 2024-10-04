package com.riwi.project.infrastructure.persistence;

import com.riwi.project.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {

}
