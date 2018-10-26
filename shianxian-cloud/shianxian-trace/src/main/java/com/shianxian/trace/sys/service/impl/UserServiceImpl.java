package com.shianxian.trace.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.config.shiro.PasswordHelper;
import com.shianxian.trace.sys.dao.UserDao;
import com.shianxian.trace.sys.pojo.User;
import com.shianxian.trace.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Iterator;
import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/17 15:04
 * @Description: 用户业务层实现
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    /**
     * 分页查询用户
     *
     * @param page
     * @return
     */
    @Override
    public Object selectUserByPage(Page page) {
        Example example = new Example(User.class);
        example.setOrderByClause("id DESC");
        // 设置分页
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<User> userList = null;
        // 获取当前用户
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        if (loginUser.getIsAdmin() == 2) {
            // 如果是超级管理员，则查询所有的用户
            userList = this.userDao.selectByExample(example);
        } else if (loginUser.getIsAdmin() == 1){
            // 如果是管理员，则查询除了超级管理员之外的所有用户
            userList = this.userDao.selectByExample(example);
            Iterator<User> iterator = userList.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getIsAdmin() == 2) {
                    iterator.remove();
                    break;
                }
            }
        } else {
            // 不是管理员，则查询自己的子用户
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id", loginUser.getId());
            userList = this.userDao.selectByExample(example);
        }
        return ResultUtils.setData(objectPage.getTotal(), userList);
    }


    /**
     * 根据id修改用户信息
     *
     * @return
     */
    @Override
    @Transactional
    public Integer updateUserById(User user) {
        user.setPassword(null);
        return this.userDao.updateByPrimaryKeySelective(user);
    }


    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public User selectByUsername(User user) {
        return this.userDao.selectOne(user);
    }


    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public Integer saveUser(User user) {
        User queryUser = new User();
        queryUser.setUsername(user.getUsername());
        User u = this.userDao.selectOne(queryUser);
        if (u != null) {
            return 2;
        }
        // 添加父id
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        if (loginUser != null) {
            user.setParentId(loginUser.getId());
        }
        user.setEnable(1);
        if (user.getIsAdmin() == null) {
            // 如果为空，则默认不是管理员
            user.setIsAdmin(0);
        }
        // 加密密码
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(user);
        return this.userDao.insertSelective(user);
    }


    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public Integer deleteUser(Integer userId) {
        return this.userDao.deleteByPrimaryKey(userId);
    }


    /**
     * 修改密码
     * @param user
     * @return
     */
    @Override
    @Transactional
    public Integer updatePassword(User user) {
        // 加密密码
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(user);
        return this.userDao.updateByPrimaryKeySelective(user);
    }

}
