package com.albumen.domain.project;

import com.albumen.project.Project;
import com.albumen.project.TimeTable;

import java.util.List;

public interface ProjectService {

    boolean submit(Project project, String contact);

    List<ProjectDto> getMyProject();

    boolean signContract(Integer contractId);

    boolean submitTimeTable(Integer projectId, List<TimeTable> tableList);
}
