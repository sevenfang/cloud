package com.shianxian.trace.base.pojo;

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
 * 加工模板
 */
@Data
@Table(name = "t_process_template")
public class ProcessTemplate extends BasePojo {

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
     * 物料id，此物料为原料
     */
    @NotNull(message = "物料id不能为空！")
    private Integer materialId;

    /**
     * 加工模板名称
     */
    @NotEmpty(message = "加工模板名称不能为空！")
    private String processTemplateName;

    /**
     * 加工模板详情集合
     */
    @Transient
    @Valid
    @NotNull(message = "加工模板详情不能为空！")
    private List<ProcessTemplateDetail> processTemplateDetails;

}
