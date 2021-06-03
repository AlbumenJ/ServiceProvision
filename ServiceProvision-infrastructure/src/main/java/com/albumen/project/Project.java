package com.albumen.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "project")
public class Project implements Serializable {
    @TableId(value = "project_id", type = IdType.AUTO)
    private Integer projectId;

    @TableField(value = "`name`")
    private String name;

    @TableField(value = "description")
    private String description;

    @TableField(value = "worker_id")
    private Integer workerId;

    @TableField(value = "customer_id")
    private Integer customerId;

    private static final long serialVersionUID = 1L;

    public static final String COL_PROJECT_ID = "project_id";

    public static final String COL_NAME = "name";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_WORKER_ID = "worker_id";

    public static final String COL_CUSTOMER_ID = "customer_id";
}