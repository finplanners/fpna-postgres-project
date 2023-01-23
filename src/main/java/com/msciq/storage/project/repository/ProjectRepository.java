package com.msciq.storage.project.repository;

import com.msciq.storage.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByBusinessUnit_Id(Long buCode);
}
