package com.shianxian.trace.sys.service;

import com.shianxian.trace.sys.pojo.RolePermission;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/21 17:18
 * @Description: 角色权限业务层接口
 */
public interface RolePermissionService {


    /**
     * 保存角色所拥有的权限
     * @param rolePermission
     * @return
     */
    Integer saveRolePermission(RolePermission rolePermission);


    /**
     * 删除角色所拥有的权限
     * @param rolePermission
     * @return
     */
    Integer deleteRolePermission(RolePermission rolePermission);
}
