package com.shianxian.trace.flow.vo;


import com.shianxian.trace.base.pojo.Material;
import lombok.Data;


/**
 * 销售单详情单VO
 */
@Data
public class SaleOrderDetailVO extends Material {


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
     * 销售单详情id
     */
    private Integer saleOrderDetailId;

}
