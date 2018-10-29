package com.shianxian.trace.flow.vo;


import com.shianxian.trace.base.pojo.Material;
import lombok.Data;

import java.util.Date;


/**
 * 商品入库单VO
 */
@Data
public class GoodsInOrderVO extends Material {


    /**
     * 物料id
     */
    private Integer materialId;

    /**
     * 商品入库单号
     */
    private String goodsInOrderNo;

    /**
     * 商品入库日期
     */
    private Date goodsInOrderTime;

    /**
     * 商品入库数量
     */
    private Integer processGoodsNum;

    /**
     * 物料加工单id
     */
    private Integer processMaterialId;

    /**
     * 物料加工单号
     */
    private String processMaterialNo;

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
