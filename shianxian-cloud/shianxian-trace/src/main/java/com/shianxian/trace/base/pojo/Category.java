package com.shianxian.trace.base.pojo;


import com.shianxian.trace.common.pojo.BasePojo;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * 物料类别
 */
@Table(name = "t_category")
@Data
public class Category extends BasePojo {


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
     * 类别名称
     */
    @NotEmpty(message = "物料类别名称不能为空！")
    private String categoryName;

    /**
     * 类别分类。1为原料，2为商品
     */
    @NotNull(message = "类别分类名称不能为空！")
    private Integer categoryType;

    /**
     * 类别描述
     */
    private String description;

}
