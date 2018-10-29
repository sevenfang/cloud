package com.shianxian.trace.flow.pojo;


import com.shianxian.trace.common.pojo.BasePojo;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


/**
 * 销售单
 */
@Data
@Table(name = "t_sale_order")
public class SaleOrder extends BasePojo {


    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 企业id
     */
    @NotNull(message = "企业id不能为空！")
    private Integer companyId;

    /**
     * 销售单号
     */
    private String saleOrderNo;

    /**
     * 销售单日期
     */
    @NotNull(message = "销售单日期不能为空！")
    private Date saleOrderTime;

    /**
     * 订单状态。1：待审核，2：已审核，3：未发货，4：已发货，5：已收货
     */
    @NotNull(message = "订单状态不能为空！")
    private Integer status;

    /**
     * 销售单详情
     */
    @Valid
    @Transient
    private List<SaleOrderDetail> saleOrderDetails;

}
