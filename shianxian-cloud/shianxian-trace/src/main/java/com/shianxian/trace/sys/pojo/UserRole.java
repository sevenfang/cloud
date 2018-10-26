package com.shianxian.trace.sys.pojo;

import lombok.Data;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/19 11:09
 * @Description: 用户角色中间表
 */
@Table(name = "sys_user_role")
@Data
public class UserRole {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空！")
    private Integer userId;

    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空！")
    private Integer roleId;
}
