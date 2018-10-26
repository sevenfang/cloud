package com.shianxian.trace.flow.pojo;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * 采购入库详情表
 */
@Data
@Table(name = "t_purchase_in_order_detail")
public class PurchaseInOrderDetail {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 采购入库单id
     */
    private Integer purchaseInOrderId;

    /**
     * 供应商采购入库单号，为供应商自己的进货单号
     */
    private String supplierInOrderNo;

    /**
     * 采购入库的物料数量
     */
    @NotNull(message = "采购入库的物料数量不能为空！")
    private Integer purchaseInOrderNum;

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
     * 供应商id
     */
    @NotNull(message = "供应商id不能为空！")
    private Integer supplierId;

    /**
     * 检疫证号
     */
    private String quarantineNo;
}
