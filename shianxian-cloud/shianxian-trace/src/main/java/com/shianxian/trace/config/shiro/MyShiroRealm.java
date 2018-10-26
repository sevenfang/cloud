package com.shianxian.trace.config.shiro;

import com.shianxian.trace.sys.dao.RoleDao;
import com.shianxian.trace.sys.pojo.Permission;
import com.shianxian.trace.sys.pojo.Role;
import com.shianxian.trace.sys.pojo.User;
import com.shianxian.trace.sys.service.PermissionService;
import com.shianxian.trace.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;


/**
 * 授权、认证
 */
public class MyShiroRealm extends AuthorizingRealm {


    @Resource
    private UserService userService;

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionService permissionService;


    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Permission> permissionList = this.permissionService.loadUserPermission(user);
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for(Permission permission: permissionList){
            // 添加权限
            info.addStringPermission(permission.getPermissionCode());
        }
        List<Role> roleList = this.roleDao.selectRoleByUserId(user.getId());
        for (Role role : roleList) {
            // 添加角色
            info.addRole(role.getRoleName());
        }
        return info;

    }


    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        User queryUser = new User();
        queryUser.setUsername(username);
        User user = userService.selectByUsername(queryUser);
        if(user==null) throw new UnknownAccountException();
        if (user.getEnable() == 0) {
            throw new LockedAccountException(); // 帐号锁定
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, // 用户
                user.getPassword(), // 密码
                ByteSource.Util.bytes(username),
                getName()  // realm name
        );

        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", user);
        session.setAttribute("userSessionId", user.getId());
        return authenticationInfo;
    }

}