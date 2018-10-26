package com.shianxian.trace.flow.vo;


import lombok.Data;


/**
 * 商品出库单详情VO
 */
@Data
public class GoodsOutOrderDetailVO {


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

    /**
     * 订单状态。1：待审核，2：已审核，3：未发货，4：已发货，5：已收货
     */
    private Integer status;

}
