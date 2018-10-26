package com.shianxian.trace.flow.pojo;


import com.shianxian.trace.common.pojo.BasePojo;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * 原料出库单
 */
@Table(name = "t_raw_out_order")
@Data
public class RawOutOrder extends BasePojo {


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
     * 原料出库单号
     */
    private String rawOutOrderNo;

    /**
     * 原料出库日期
     */
    private Date rawOutOrderTime;

    /**
     * 加工id
     */
    private Integer processMaterialId;

    /**
     * 物料加工单号
     */
    private String processMaterialNo;

    /**
     * 物料加工数量，0：加工库存里所有的这个原料
     */
    private Integer processMaterialNum;

    /**
     * 物料id，加工的原料
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
