package com.shianxian.trace.flow.pojo;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 销售单详情
 */
@Table(name = "t_sale_order_detail")
@Data
public class SaleOrderDetail {


    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 销售单id
     */
    private Integer saleOrderId;

    /**
     * 物料id
     */
    @NotNull(message = "企业id不能为空！")
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
     * 销售物料数量
     */
    @NotNull(message = "销售物料数量不能为空！")
    private Integer saleNum;

}
