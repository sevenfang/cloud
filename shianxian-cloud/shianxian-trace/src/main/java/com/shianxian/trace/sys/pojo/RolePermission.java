package com.shianxian.trace.sys.pojo;

import lombok.Data;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/19 11:13
 * @Description: 角色权限中间表
 */
@Table(name = "sys_role_permission")
@Data
public class RolePermission {


    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空！")
    private Integer roleId;

    /**
     * 权限id
     */
    @NotNull(message = "权限id不能为空！")
    private Integer permissionId;
}
