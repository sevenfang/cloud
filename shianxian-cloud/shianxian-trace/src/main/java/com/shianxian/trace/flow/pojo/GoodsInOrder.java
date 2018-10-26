package com.shianxian.trace.flow.pojo;


import com.shianxian.trace.common.pojo.BasePojo;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * 商品入库单
 */
@Table(name = "t_goods_in_order")
@Data
public class GoodsInOrder extends BasePojo {


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
     * 商品入库单号
     */
    private String goodsInOrderNo;

    /**
     * 商品入库日期
     */
    private Date goodsInOrderTime;

    /**
     * 商品入库数量
     */
    private Integer processGoodsNum;

    /**
     * 物料加工单id
     */
    private Integer processMaterialId;

    /**
     * 物料加工单号
     */
    private String processMaterialNo;

    /**
     * 物料id
     */
    private Integer materialId;

    /**
     * 物料类别id
     */
    private Integer categoryId;

    /**
     * 计量单位id
     */
    private Integer unitId;

    /**
     * 审核状态。1：待审核，2：审核不过，3：审核通过。默认是审核通过
     */
    private Integer status = 3;

}
