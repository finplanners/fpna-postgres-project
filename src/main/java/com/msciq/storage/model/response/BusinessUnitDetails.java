package com.msciq.storage.model.response;

import com.msciq.storage.common.entity.Location;
import com.msciq.storage.model.Project;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BusinessUnitDetails {
        private List<Project> projects;
        private List<Location> locations;
}
