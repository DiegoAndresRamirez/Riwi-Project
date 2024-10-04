package com.riwi.project.infrastructure.persistence;

import com.riwi.project.domain.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
