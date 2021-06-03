package com.albumen.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "contract")
public class Contract implements Serializable {
    @TableField(value = "contract_id")
    private Integer contractId;

    @TableField(value = "`status`")
    private Integer status;

    @TableField(value = "detail")
    private String detail;

    @TableField(value = "sign_date")
    private Date signDate;

    private static final long serialVersionUID = 1L;

    public static final String COL_CONTRACT_ID = "contract_id";

    public static final String COL_STATUS = "status";

    public static final String COL_DETAIL = "detail";

    public static final String COL_SIGN_DATE = "sign_date";
}