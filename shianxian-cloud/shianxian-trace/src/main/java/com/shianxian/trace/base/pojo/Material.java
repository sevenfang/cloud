package com.shianxian.trace.base.pojo;


import com.shianxian.trace.common.pojo.BasePojo;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 物料
 */
@Table(name = "t_material")
@Data
public class Material extends BasePojo {


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
     * 物料名称
     */
    @NotEmpty(message = "物料名称不能为空！")
    private String materialName;

    /**
     * 物料类型。1：原料。2：商品
     */
    private Integer materialType;

    /**
     * 国家标准编码
     */
    private String nationCode;

    /**
     * 国家标准名称
     */
    private String nationName;

    /**
     * 物料类别id
     */
    @NotNull(message = "物料类别id不能为空！")
    private Integer categoryId;

    /**
     * 是否半成品，1：是  0：不是
     */
    @NotEmpty(message = "是否半成品不能为空！")
    private String isProduct;

    /**
     * 计量单位id
     */
    @NotNull(message = "计量单位id不能为空！")
    private Integer unitId;

    /**
     * 产品规格
     */
    @NotEmpty(message = "产品规格不能为空！")
    private String standard;

    /**
     * 产品图片
     */
    private String images;

    /**
     * 物料库存
     */
    private Integer num;

}
