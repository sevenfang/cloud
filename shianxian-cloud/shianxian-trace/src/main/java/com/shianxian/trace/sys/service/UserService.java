package com.shianxian.trace.sys.service;


import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.sys.pojo.User;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/17 15:02
 * @Description: 用户业务层接口
 */
public interface UserService {

    /**
     * 分页查询用户
     * @param page
     * @return
     */
    Object selectUserByPage(Page page);


    /**
     * 根据id修改用户信息
     * @return
     */
    Integer updateUserById(User user);


    /**
     * 用户登录
     * @param user
     * @return
     */
    User selectByUsername(User user);


    /**
     * 保存用户
     * @param user
     * @return
     */
    Integer saveUser(User user);


    /**
     * 删除用户
     * @param userId
     * @return
     */
    Integer deleteUser(Integer userId);


    /**
     * 修改密码
     * @return
     */
    Integer updatePassword(User user);
}
