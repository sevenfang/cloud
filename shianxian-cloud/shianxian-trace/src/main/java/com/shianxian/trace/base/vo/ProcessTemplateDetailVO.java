package com.shianxian.trace.base.vo;


import com.shianxian.trace.base.pojo.Material;
import lombok.Data;

/**
 * 加工模板明细
 */
@Data
public class ProcessTemplateDetailVO extends Material {


    /**
     * 物料id
     */
    private Integer materialId;

    /**
     * 计量单位名称
     */
    private String unitName;

    /**
     * 物料类别名称
     */
    private String categoryName;

    /**
     * 物料类别名称
     */
    private String processTemplateNum;

}
