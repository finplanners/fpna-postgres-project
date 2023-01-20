package com.msciq.storage.project.service.Impl;

import com.msciq.storage.model.Project;
import com.msciq.storage.model.request.ProjectDTO;
import com.msciq.storage.project.repository.ProjectRepository;
import com.msciq.storage.project.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    ModelMapper modelMapper = new ModelMapper();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> addProjects(List<ProjectDTO> projectDTOS) {
        List<Project> projects = new ArrayList<>();
        projectDTOS.stream().forEach(projectDTO -> {
            projects.add(modelMapper.map(projectDTO, Project.class));
        });
        return projectRepository.saveAll(projects);
    }

    @Override
    public List<Project> getAllProjectsByBusinessunit(Long buCode) {
        return projectRepository.findByBusinessUnit_Id(buCode);
    }
}
