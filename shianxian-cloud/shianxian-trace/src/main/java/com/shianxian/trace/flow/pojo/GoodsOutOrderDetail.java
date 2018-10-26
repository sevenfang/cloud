package com.shianxian.trace.flow.pojo;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * 商品出库单详情
 */
@Table(name = "t_goods_out_order_detail")
@Data
public class GoodsOutOrderDetail {


    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 商品出库单id
     */
    @NotNull(message = "商品出库单id不能为空！")
    private Integer goodsOutOrderId;

    /**
     * 物料id
     */
    @NotNull(message = "物料id不能为空！")
    private Integer materialId;

    /**
     * 物料类别id
     */
    @NotNull(message = "物料类别id不能为空！")
    private Integer categoryId;

    /**
     * 计量单位id
     */
    @NotNull(message = "计量单位id不能为空！")
    private Integer unitId;

    /**
     * 商品出库数量
     */
    private Integer goodsNum;

    /**
     * 商品追溯码
     */
    private String goodsTraceCode;

    /**
     * 订单状态。1：待审核，2：已审核，3：未发货，4：已发货，5：已收货
     */
    @NotNull(message = "订单状态不能为空！")
    private Integer status;

}
