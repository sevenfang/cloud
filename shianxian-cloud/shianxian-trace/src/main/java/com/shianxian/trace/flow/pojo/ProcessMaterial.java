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
import java.util.List;


/**
 * 物料加工单
 */
@Table(name = "t_process_material")
@Data
public class ProcessMaterial extends BasePojo {

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
     * 物料加工单号
     */
    @NotEmpty(message = "物料加工单号不能为空！")
    private String processMaterialNo;

    /**
     * 物料加工数量，-1：加工库存里所有的这个原料
     */
    @NotNull(message = "物料加工数量不能为空！")
    private Integer processMaterialNum;

    /**
     * 物料id
     */
    @NotNull(message = "物料id不能为空！")
    private Integer materialId;

    /**
     * 物料类别id
     */
    @NotNull(message = "物料类别id不能为空！")
    private Integer categoryId;

    /**
     * 计量单位id
     */
    @NotNull(message = "计量单位id不能为空！")
    private Integer unitId;

    /**
     * 物料加工单详情集合
     */
    @Valid
    @Transient
    private List<ProcessMaterialDetail> processMaterialDetails;

}
