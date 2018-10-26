package com.shianxian.trace.flow.pojo;


import com.shianxian.trace.common.pojo.BasePojo;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
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
    private Integer companyId;

    /**
     * 商品出库单号
     */
    private String goodsOutOrderNo;

    /**
     * 商品出库日期
     */
    private Date goodsOutOrderTime;

    /**
     * 销售单id
     */
    private Integer saleOrderId;

    /**
     * 销售单号
     */
    private String saleOrderNo;

    /**
     * 操作员
     */
    private String operatorUser;

    /**
     * 发货人
     */
    private String consignerUser;

    /**
     * 商品出库单详情
     */
    @Valid
    @Transient
    private List<GoodsOutOrderDetail> goodsOutOrderDetails;
}
