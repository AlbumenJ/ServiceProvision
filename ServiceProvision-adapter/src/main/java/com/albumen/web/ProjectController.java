package com.albumen.web;

import com.albumen.domain.project.ProjectDto;
import com.albumen.domain.project.ProjectService;
import com.albumen.project.Project;
import com.albumen.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/submit")
    public CommonResult<Void> submit(Project project, @RequestParam("contact") String contact) {
        projectService.submit(project, contact);
        return new CommonResult<Void>().success();
    }

    @RequestMapping("/getMyProject")
    public CommonResult<List<ProjectDto>> getMyProject() {
        return new CommonResult<List<ProjectDto>>().success(projectService.getMyProject());
    }

    @RequestMapping("/signContract")
    public CommonResult<Void> signContract(@RequestParam("contractId") Integer contractId) {
        return new CommonResult<Void>().setSuccess(projectService.signContract(contractId));
    }
}
