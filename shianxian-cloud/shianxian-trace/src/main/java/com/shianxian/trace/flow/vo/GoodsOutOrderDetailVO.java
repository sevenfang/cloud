package com.shianxian.trace.flow.vo;


import lombok.Data;


/**
 * 商品出库单详情VO
 */
@Data
public class GoodsOutOrderDetailVO {

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
     * 商品出库单详情id
     */
    private Integer GoodsOutOrderDetailId;

    /**
     * 商品追溯码
     */
    private String goodsTraceCode;

}
