package com.shianxian.trace.flow.pojo;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * 物料加工详情
 */
@Table(name = "t_process_material_detail")
@Data
public class ProcessMaterialDetail {


    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 物料加工主表id
     */
    private Integer processMaterialId;

    /**
     * 加工数量，加工后的商品
     */
    @NotNull(message = "加工商品数量不能为空！")
    private Integer processGoodsNum;

    /**
     * 物料id
     */
    @NotNull(message = "物料id不能为空！")
    private Integer materialId;

    /**
     * 类别id
     */
    @NotNull(message = "类别id不能为空！")
    private Integer categoryId;

    /**
     * 计量单位id
     */
    @NotNull(message = "计量单位id不能为空！")
    private Integer unitId;

}
