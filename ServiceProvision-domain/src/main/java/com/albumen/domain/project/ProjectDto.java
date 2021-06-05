package com.albumen.domain.project;

import com.albumen.project.TimeTable;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectDto {
    private Integer projectId;
    private String name;
    private String description;
    private Integer workerId;
    private String workerName;
    private Integer customerId;
    private String customerName;
    private Integer status;
    private String detail;
    private Date signDate;
    private List<TimeTable> timeTableList;

}
