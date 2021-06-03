package com.albumen.customer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "customer")
public class Customer implements Serializable {
    @TableField(value = "customer_id")
    private Integer customerId;

    @TableField(value = "`name`")
    private String name;

    @TableField(value = "category")
    private String category;

    private static final long serialVersionUID = 1L;

    public static final String COL_CUSTOMER_ID = "customer_id";

    public static final String COL_NAME = "name";

    public static final String COL_CATEGORY = "category";
}