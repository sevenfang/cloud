package com.shianxian.trace.flow.vo;


import com.shianxian.trace.base.pojo.Material;
import lombok.Data;


/**
 * 销售单详情单VO
 */
@Data
public class SaleOrderDetailVO extends Material {


    /**
     * 计量单位名称
     */
    private String unitName;

    /**
     * 物料类别名称
     */
    private String categoryName;

    /**
     * 销售单详情id
     */
    private Integer saleOrderDetailId;

    /**
     * 订单状态。1：待审核，2：已审核，3：未发货，4：已发货，5：已收货
     */
    private Integer status;
}