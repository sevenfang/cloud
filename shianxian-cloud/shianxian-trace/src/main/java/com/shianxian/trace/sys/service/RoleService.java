package com.shianxian.trace.sys.service;

import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.sys.pojo.Role;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/20 17:29
 * @Description: 角色业务层接口
 */
public interface RoleService {

    /**
     * 保存、修改角色
     * @param role
     * @return
     */
    Integer saveOrUpdateRole(Role role);


    /**
     * 删除角色
     * @param roleId
     * @return
     */
    Integer deleteRole(Integer roleId) throws Exception;


    /**
     * 分页查询角色
     * @param page
     * @return
     */
    Object selectRoleByPage(Page page);
}
