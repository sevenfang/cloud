package com.shianxian.trace.sys.dao;

import com.shianxian.trace.sys.pojo.Role;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/19 11:06
 * @Description: 角色持久层接口
 */
@Repository
public interface RoleDao extends Mapper<Role> {

    /**
     * 根据用户id查询所拥有的角色
     * @param userId
     * @return
     */
    List<Role> selectRoleByUserId(Integer userId);
}
