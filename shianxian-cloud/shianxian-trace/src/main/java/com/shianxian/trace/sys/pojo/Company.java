package com.shianxian.trace.sys.pojo;


import com.shianxian.trace.common.pojo.BasePojo;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * 企业
 */
@Table(name = "t_company")
@Data
public class Company extends BasePojo {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 企业名称
     */
    @NotEmpty(message = "企业名称不能为空！")
    private String name;

    /**
     * 企业简称
     */
    private String shortName;

    /**
     * 企业节点码
     */
    private String companyCode;

    /**
     * 追溯节点码
     */
    @NotEmpty(message = "追溯节点码不能为空！")
    private String traceCode;

    /**
     * 经营地址
     */
    @NotEmpty(message = "经营地址不能为空！")
    private String address;

    /**
     * 统一社会代码/证件号码
     */
    @NotEmpty(message = "统一社会代码/证件号码不能为空！")
    private String code;

    /**
     * 法人代表
     */
    @NotEmpty(message = "法人代表不能为空！")
    private String corporate;

    /**
     * 联系人
     */
    @NotEmpty(message = "联系人不能为空！")
    private String contacts;

    /**
     * 联系电话
     */
    @NotEmpty(message = "联系电话不能为空！")
    private String tel;

    /**
     * 企业类型
     */
    @NotEmpty(message = "企业类型不能为空！")
    private String companyType;

    /**
     * 经营领域
     */
    @NotEmpty(message = "经营领域不能为空！")
    private String territory;

    /**
     * 主营业务
     */
    @NotEmpty(message = "主营业务不能为空！")
    private String business;

    /**
     * 经营范围
     */
    private String scope;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 是否有效  1：有效  0：无效。默认有效
     */
    private Integer enable = 1;

}
