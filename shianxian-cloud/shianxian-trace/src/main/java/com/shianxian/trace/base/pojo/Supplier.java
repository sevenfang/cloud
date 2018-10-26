package com.shianxian.trace.base.pojo;


import com.shianxian.trace.common.pojo.BasePojo;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * 供应商
 */
@Data
@Table(name = "t_supplier")
public class Supplier extends BasePojo {

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
     * 供应商名称
     */
    @NotEmpty(message = "供应商名称不能为空！")
    private String supplierName;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 经营地址
     */
    private String address;

    /**
     * 营业执照/证件号
     */
    @NotEmpty(message = "营业执照/证件号不能为空！")
    private String businessLicense;

    /**
     * 简介
     */
    private String intro;

    /**
     * 是否有效  1：有效  0：无效。默认有效
     */
    private Integer enable = 1;

}
