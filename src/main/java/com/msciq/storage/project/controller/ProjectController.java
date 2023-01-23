package com.msciq.storage.project.controller;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.SuccessMessage;
import com.msciq.storage.exception.BadRequestException;
import com.msciq.storage.model.Project;
import com.msciq.storage.model.request.ProjectDTO;
import com.msciq.storage.model.response.SuccessResponse;
import com.msciq.storage.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/project")
@Validated
public class ProjectController {

    @Autowired
    ProjectService projectService;

    /**
     * Gets all forecasting templates.
     *
     * @return List of project entity
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public SuccessResponse<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new SuccessResponse<List<Project>>("Success", projects, null, HttpStatus.OK);
    }

    /**
     * This method is used to add a new Template.
     *
     * @param projectDTOS
     * @return List of project
     * @author Sivaranjani DS
     */
    @RequestMapping(method = RequestMethod.POST)
    public SuccessResponse<List<Project>> addProjects(@RequestBody List<ProjectDTO> projectDTOS) {
        if (projectDTOS.isEmpty()) {
            throw new BadRequestException(19011);
        }
        List<Project> projects = projectService.addProjects(projectDTOS);
        return new SuccessResponse<List<Project>>
                (String.format(SuccessMessage.SUCCESSFULLY_SAVED, Constants.PROFIT_CENTER),
                        projects,
                        null,
                        HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{buCode}/list", method = RequestMethod.GET)
    public SuccessResponse<List<Project>> getAllProjectsByBusinessunit(
            @PathVariable(value = "buCode") Long buCode
    ) {
        List<Project> projects = projectService.getAllProjectsByBusinessunit(buCode);
        return new SuccessResponse<List<Project>>
                (String.format(SuccessMessage.SUCCESS, Constants.PROJECT),
                        projects,
                        null,
                        HttpStatus.OK);
    }
}
