package com.riwi.project.infrastructure.persistence;

import com.riwi.project.application.dto.response.TaskCreateDTO;
import com.riwi.project.domain.model.Task;
import com.riwi.project.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByUser(User user);
}
