package com.shianxian.trace.base.pojo;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 加工模板明细
 */
@Data
@Table(name = "t_process_template_detail")
public class ProcessTemplateDetail {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 加工模板id
     */
    private Integer processTemplateId;

    /**
     * 物料类别id
     */
    @NotNull(message = "物料类型id不能为空！")
    private Integer categoryId;

    /**
     * 计量单位id
     */
    @NotNull(message = "计量单位id不能为空！")
    private Integer unitId;

    /**
     * 物料id，此物料为加工过后的商品
     */
    @NotNull(message = "物料id不能为空！")
    private Integer materialId;

    /**
     * 加工模板数量
     */
    @NotNull(message = "加工数量不能为空！")
    private Integer processTemplateNum;

}
