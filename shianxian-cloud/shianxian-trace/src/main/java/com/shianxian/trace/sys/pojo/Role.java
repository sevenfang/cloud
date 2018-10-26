package com.shianxian.trace.sys.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/19 11:07
 * @Description: 角色
 */
@Table(name = "sys_role")
@Data
public class Role {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 角色名
     */
    @NotEmpty(message = "角色名称不能为空！")
    private String roleName;

    /**
     * 角色描述
     */
    private String description;
}
