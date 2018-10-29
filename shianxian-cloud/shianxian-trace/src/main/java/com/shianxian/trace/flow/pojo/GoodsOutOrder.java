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
 * 商品出库单
 */
@Table(name = "t_goods_out_order")
@Data
public class GoodsOutOrder extends BasePojo {

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
     * 商品出库单号
     */
    @NotEmpty(message = "商品出库单号不能为空！")
    private String goodsOutOrderNo;

    /**
     * 商品出库日期
     */
    @NotNull(message = "商品出库日期不能为空！")
    private Date goodsOutOrderTime;

    /**
     * 销售单id
     */
    @NotNull(message = "销售单id不能为空！")
    private Integer saleOrderId;

    /**
     * 销售单号
     */
    @NotEmpty(message = "销售单号不能为空！")
    private String saleOrderNo;

    /**
     * 操作员
     */
    @NotEmpty(message = "操作员不能为空！")
    private String operatorUser;

    /**
     * 发货人
     */
    @NotEmpty(message = "发货人不能为空！")
    private String consignerUser;

    /**
     * 订单状态。1：待审核，2：已审核，3：未发货，4：已发货，5：已收货
     */
    @NotNull(message = "订单状态不能为空！")
    private Integer status;

    /**
     * 商品出库单详情
     */
    @Valid
    @Transient
    private List<GoodsOutOrderDetail> goodsOutOrderDetails;
}
