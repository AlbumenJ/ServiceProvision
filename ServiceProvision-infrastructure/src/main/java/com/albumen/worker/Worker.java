package com.albumen.worker;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "worker")
public class Worker implements Serializable {
    @TableField(value = "worker_id")
    private Integer workerId;

    @TableField(value = "category")
    private String category;

    @TableField(value = "description")
    private String description;

    @TableField(value = "contact")
    private String contact;

    @TableField(value = "`name`")
    private String name;

    private static final long serialVersionUID = 1L;

    public static final String COL_WORKER_ID = "worker_id";

    public static final String COL_CATEGORY = "category";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CONTACT = "contact";

    public static final String COL_NAME = "name";
}