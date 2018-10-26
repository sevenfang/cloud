package com.shianxian.trace.sys.dao;

import com.shianxian.trace.sys.pojo.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/17 15:05
 * @Description: 用户持久层接口
 */
@Repository
public interface UserDao extends Mapper<User> {
}
