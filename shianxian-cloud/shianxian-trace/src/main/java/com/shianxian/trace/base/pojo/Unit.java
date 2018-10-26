package com.shianxian.trace.base.pojo;


import com.shianxian.trace.common.pojo.BasePojo;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 计量单位
 */
@Table(name = "t_unit")
@Data
public class Unit extends BasePojo {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 企业id
     */
    @NotNull(message = "企业id不能为空！")
    private Integer companyId;

    /**
     * 计量单位名称
     */
    @NotEmpty(message = "计量单位名称不能为空！")
    private String unitName;

    /**
     * 计量单位描述
     */
    private String description;


}
