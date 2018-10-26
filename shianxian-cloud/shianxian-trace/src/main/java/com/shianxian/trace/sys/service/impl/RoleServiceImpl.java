package com.shianxian.trace.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.sys.dao.RoleDao;
import com.shianxian.trace.sys.dao.RolePermissionDao;
import com.shianxian.trace.sys.pojo.Role;
import com.shianxian.trace.sys.pojo.RolePermission;
import com.shianxian.trace.sys.pojo.User;
import com.shianxian.trace.sys.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/20 17:32
 * @Description: 角色业务层实现
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;


    /**
     * 保存、修改角色
     * @param role
     * @return
     */
    @Transactional
    @Override
    public Integer saveOrUpdateRole(Role role) {
        // 判断角色名称不能重复
        Role quertRole = new Role();
        quertRole.setRoleName(role.getRoleName());
        List<Role> roles = this.roleDao.select(quertRole);
        if (!roles.isEmpty()) {
            return 2;
        }
        if (role.getId() == null) {
            return this.roleDao.insertSelective(role);
        } else {
            return this.roleDao.updateByPrimaryKeySelective(role);
        }
    }


    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @Override
    @Transactional
    public Integer deleteRole(Integer roleId) throws Exception {
        int count = this.roleDao.deleteByPrimaryKey(roleId);
        if (count == 1) {
            // 级联删除这个角色所拥有的权限
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            this.rolePermissionDao.delete(rolePermission);
            return count;
        } else {
            throw new RuntimeException("删除角色错误！");
        }
    }


    /**
     * 分页查询角色
     * @param page
     * @return
     */
    @Override
    public Object selectRoleByPage(Page page) {
        List<Role> roleList = null;
        // 获取当前登录用户，如果不是父id不是admin，那么只能查询自己所拥有的角色
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        com.github.pagehelper.Page<Object> pages = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        if (loginUser.getId() == 1) {
            // 是admin
            roleList = this.roleDao.selectAll();
        } else {
            // 不是admin
            roleList = this.roleDao.selectRoleByUserId(loginUser.getId());
        }
        long total = pages.getTotal();
        return ResultUtils.setData(total, roleList);
    }
}
