package com.shianxian.trace.flow.pojo;


import com.shianxian.trace.common.pojo.BasePojo;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


/**
 * 采购入库单
 */
@Data
@Table(name = "t_purchase_in_order")
public class PurchaseInOrder extends BasePojo {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 采购入库单号
     */
    @NotEmpty(message = "采购入库单号不能为空！")
    private String purchaseInOrderNo;

    /**
     * 原料入库日期
     */
    @NotNull(message = "原料入库日期不能为空！")
    private Date materialInTime;

    /**
     * 审核状态。1：待审核，2：审核中，3：审核通过
     */
    @NotNull(message = "审核状态不能为空！")
    private Integer status;

    /**
     * 企业id
     */
    @NotNull(message = "企业id不能为空！")
    private Integer companyId;


    /**
     * 采购入库单详情
     */
    @Valid
    @Transient
    @NotNull(message = "采购入库单详情不能为空！")
    private List<PurchaseInOrderDetail> purchaseInOrderDetails;
}
