package com.albumen.domain.project;

import com.albumen.auth.util.SecurityUtil;
import com.albumen.customer.CustomerMapper;
import com.albumen.project.Contract;
import com.albumen.project.ContractMapper;
import com.albumen.project.Project;
import com.albumen.project.ProjectMapper;
import com.albumen.project.TimeTable;
import com.albumen.project.TimeTableMapper;
import com.albumen.user.User;
import com.albumen.user.UserMapper;
import com.albumen.worker.WorkerMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private WorkerMapper workerMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private TimeTableMapper timeTableMapper;

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

    @Override
    public List<ProjectDto> getMyProject() {
        User user = SecurityUtil.getUserDetail(User.class);

        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        if (user.getRoleId() == 1) {
            // worker
            queryWrapper.lambda().eq(Project::getWorkerId, user.getUserId());
        } else {
            queryWrapper.lambda().eq(Project::getCustomerId, user.getUserId());
        }

        List<Project> projectList = projectMapper.selectList(queryWrapper);
        if (projectList.size() == 0) {
            return null;
        }

        return projectList.stream().map(project -> {
            ProjectDto projectDto = new ProjectDto();
            BeanUtils.copyProperties(project, projectDto);
            projectDto.setWorkerName(workerMapper.selectById(project.getWorkerId()).getName());
            projectDto.setCustomerName(customerMapper.selectById(project.getCustomerId()).getName());

            Contract contract = contractMapper.selectById(project.getProjectId());
            BeanUtils.copyProperties(contract, projectDto);

            QueryWrapper<TimeTable> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.lambda().eq(TimeTable::getProjectId, project.getProjectId());
            projectDto.setTimeTableList(timeTableMapper.selectList(queryWrapper1));

            return projectDto;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean signContract(Integer contractId) {
        UpdateWrapper<Contract> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(Contract::getContractId, contractId)
                .set(Contract::getStatus, 1)
                .set(Contract::getSignDate, new Date());

        return contractMapper.update(null, updateWrapper) == 1;
    }

    @Override
    public boolean submitTimeTable(Integer projectId, List<TimeTable> tableList) {
        QueryWrapper<TimeTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TimeTable::getProjectId, projectId);
        timeTableMapper.delete(queryWrapper);

        tableList.forEach(timeTable -> {
            timeTable.setTimeTableId(null);
            timeTable.setProjectId(projectId);
            timeTableMapper.insert(timeTable);
        });

        return true;
    }
}
