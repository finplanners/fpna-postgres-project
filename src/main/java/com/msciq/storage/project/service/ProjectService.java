package com.msciq.storage.project.service;

import com.msciq.storage.model.Project;
import com.msciq.storage.model.request.ProjectDTO;

import java.util.List;

public interface ProjectService {

    /**
     * This method returns list of all Project
     *
     * @return List of Project Entity
     * @author Sivaranjani DS
     */
    public List<Project> getAllProjects();

    /**
     * This method adds list of template
     *
     * @param projects
     * @return Project Entity
     * @author Sivaranjani DS
     */
    public List<Project> addProjects(List<ProjectDTO> projects);

    public List<Project> getAllProjectsByBusinessunit(Long buCode);
}
