package com.shianxian.trace.sys.service;

import com.shianxian.trace.sys.pojo.Permission;
import com.shianxian.trace.sys.pojo.User;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/19 14:40
 * @Description: 权限业务层接口
 */
public interface PermissionService {

    /**
     * 根据用户获取相关权限
     * @param user
     * @return
     */
    List<Permission> loadUserPermission(User user);

    /**
     * 查询所有权限
     * @return
     */
    List<Permission> selectAll();


    /**
     * 根据角色id查询角色权限
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionByRoleId(Integer roleId);
}
