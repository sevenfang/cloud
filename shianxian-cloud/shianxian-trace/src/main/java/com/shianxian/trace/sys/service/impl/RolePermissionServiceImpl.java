package com.shianxian.trace.sys.service.impl;

import com.shianxian.trace.config.shiro.ShiroService;
import com.shianxian.trace.sys.dao.RolePermissionDao;
import com.shianxian.trace.sys.pojo.RolePermission;
import com.shianxian.trace.sys.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/21 17:19
 * @Description: 角色权限业务层实现
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {


    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private ShiroService shiroService;


    /**
     * 保存角色所拥有的权限
     * @param rolePermission
     * @return
     */
    @Override
    @Transactional
    public Integer saveRolePermission(RolePermission rolePermission) {
        Integer flag = this.rolePermissionDao.insertSelective(rolePermission);
        // 刷新权限
        this.shiroService.reloadPermission();
        return flag;
    }


    /**
     * 删除角色所拥有的权限
     * @param rolePermission
     * @return
     */
    @Override
    @Transactional
    public Integer deleteRolePermission(RolePermission rolePermission) {
        return this.rolePermissionDao.delete(rolePermission);
    }
}
