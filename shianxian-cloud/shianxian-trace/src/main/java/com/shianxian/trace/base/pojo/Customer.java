package com.shianxian.trace.base.pojo;


import com.shianxian.trace.common.pojo.BasePojo;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * 客户
 */
@Data
@Table(name = "t_customer")
public class Customer extends BasePojo {

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
     * 客户名称
     */
    @NotEmpty(message = "客户名称不能为空！")
    private String customerName;

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
     * 追溯节点码
     */
    @NotEmpty(message = "追溯节点码不能为空！")
    private String customerCode;

    /**
     * 营业执照/证件号
     */
    @NotEmpty(message = "营业执照/证件号不能为空！")
    private String businessLicense;

    /**
     * 是否有效  1：有效  0：无效。默认有效
     */
    private Integer enable = 1;

}
