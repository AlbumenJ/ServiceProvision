package com.albumen.domain.project;

import com.albumen.auth.util.SecurityUtil;
import com.albumen.project.Contract;
import com.albumen.project.ContractMapper;
import com.albumen.project.Project;
import com.albumen.project.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ContractMapper contractMapper;

    @Override
    public boolean submit(Project project, String contractDetail) {
        project.setCustomerId(Integer.parseInt(SecurityUtil.getUserId()));
        projectMapper.insert(project);

        Contract contract = new Contract();
        contract.setContractId(project.getProjectId());
        contract.setDetail(contractDetail);
        contract.setStatus(0);

        contractMapper.insert(contract);

        return true;
    }
}
