package com.albumen.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "time_table")
public class TimeTable implements Serializable {
    @TableId(value = "time_table_id", type = IdType.AUTO)
    private Integer timeTableId;

    @TableField(value = "project_id")
    private Integer projectId;

    @TableField(value = "time_start")
    private String timeStart;

    @TableField(value = "time_end")
    private String timeEnd;

    @TableField(value = "job")
    private String job;

    private static final long serialVersionUID = 1L;

    public static final String COL_TIME_TABLE_ID = "time_table_id";

    public static final String COL_PROJECT_ID = "project_id";

    public static final String COL_TIME_START = "time_start";

    public static final String COL_TIME_END = "time_end";

    public static final String COL_JOB = "job";
}