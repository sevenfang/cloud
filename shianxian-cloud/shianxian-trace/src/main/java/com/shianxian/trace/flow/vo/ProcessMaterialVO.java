package com.shianxian.trace.flow.vo;

import com.shianxian.trace.base.pojo.Material;
import lombok.Data;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 14:03
 * @Description: 物料加工单VO对象
 */
@Data
public class ProcessMaterialVO extends Material {


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
     * 物料加工id
     */
    private Integer processMaterialId;

    /**
     * 物料加工单号
     */
    private String processMaterialNo;

    /**
     * 物料加工数量，0：加工库存里所有的这个原料
     */
    private Integer processMaterialNum;
}
