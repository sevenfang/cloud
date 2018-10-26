package com.shianxian.trace.flow.vo;

import com.shianxian.trace.base.pojo.Material;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 14:03
 * @Description: 物料加工单详情VO对象
 */
@Data
public class ProcessMaterialDetailVO extends Material {


    /**
     * 计量单位名称
     */
    private String unitName;

    /**
     * 物料类别名称
     */
    private String categoryName;


    /**
     * 物料加工详情表id
     */
    private Integer processMaterialDetailId;

    /**
     * 加工数量，加工后的商品
     */
    @NotNull(message = "加工商品数量不能为空！")
    private Integer processGoodsNum;
}
