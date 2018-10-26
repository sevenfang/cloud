package com.shianxian.trace.sys.service;

import com.shianxian.trace.sys.pojo.UserRole;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/20 16:37
 * @Description: 用户角色业务层接口
 */
public interface UserRoleService {

    /**
     * 保存用户角色中间表
     * @param userRole
     * @return
     */
    Integer saveUserRole(UserRole userRole);


    /**
     * 删除用户拥有的角色
     * @param userRole
     * @return
     */
    Integer deleteUserRole(UserRole userRole);
}
