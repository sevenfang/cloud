package com.shianxian.trace.sys.service.impl;

import com.shianxian.trace.sys.dao.PermissionDao;
import com.shianxian.trace.sys.pojo.Permission;
import com.shianxian.trace.sys.pojo.User;
import com.shianxian.trace.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/19 14:40
 * @Description: 权限业务层实现
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 根据用户获取相关权限
     * @param user
     * @return
     */
    @Override
    public List<Permission> loadUserPermission(User user) {
        if (user == null || StringUtils.isEmpty(user.getId())) {
            return null;
        }
        List<Permission> permissionList = this.permissionDao.loadUserPermission(user.getId());
        return permissionList;
    }


    /**
     * 查询所有权限
     * @return
     */
    @Override
    public List<Permission> selectAll() {
        return this.permissionDao.selectAll();
    }


    /**
     * 根据角色id查询角色权限
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> selectPermissionByRoleId(Integer roleId) {
        return this.permissionDao.selectPermissionByRoleId(roleId);
    }
}
