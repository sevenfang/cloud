package com.shianxian.trace.flow.vo;


import com.shianxian.trace.base.pojo.Material;
import lombok.Data;

import java.util.Date;


/**
 * 原料出库单VO
 */
@Data
public class RawOutOrderVO extends Material {


    /**
     * 原料出库单号
     */
    private String rawOutOrderNo;

    /**
     * 原料出库日期
     */
    private Date rawOutOrderTime;

    /**
     * 加工id
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

    /**
     * 审核状态。1：待审核，2：审核不过，3：审核通过。默认是审核通过
     */
    private Integer status = 3;

    /**
     * 计量单位名称
     */
    private String unitName;

    /**
     * 物料类别名称
     */
    private String categoryName;

}
