package com.shianxian.trace.sys.service.impl;

import com.shianxian.trace.config.shiro.ShiroService;
import com.shianxian.trace.sys.dao.UserRoleDao;
import com.shianxian.trace.sys.pojo.UserRole;
import com.shianxian.trace.sys.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/20 16:38
 * @Description: 用户角色业务层实现
 */
@Slf4j
@Service
public class UserRoleServiceimpl implements UserRoleService {


    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private ShiroService shiroService;


    /**
     * 保存用户角色中间表
     * @param userRole
     * @return
     */
    @Override
    @Transactional
    public Integer saveUserRole(UserRole userRole) {
        Integer flag = this.userRoleDao.insertSelective(userRole);
        // 刷新权限
        this.shiroService.reloadPermission();
        return flag;
    }


    /**
     * 删除用户拥有的角色
     * @param userRole
     * @return
     */
    @Override
    @Transactional
    public Integer deleteUserRole(UserRole userRole) {
        return this.userRoleDao.delete(userRole);
    }
}
