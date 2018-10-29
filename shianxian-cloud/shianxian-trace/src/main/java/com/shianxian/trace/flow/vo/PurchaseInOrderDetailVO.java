package com.shianxian.trace.flow.vo;

import com.shianxian.trace.base.pojo.Material;
import lombok.Data;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/27 15:18
 * @Description: 采购入库单详情VO
 */
@Data
public class PurchaseInOrderDetailVO extends Material {

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
     * 供应商id
     */
    private String supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

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
    private Integer purchaseInOrderNum;

    /**
     * 采购入库的物料数量（用来计算库存）
     */
    private Integer purchaseInOrderSurplusNum;

    /**
     * 检疫证号
     */
    private String quarantineNo;
}
