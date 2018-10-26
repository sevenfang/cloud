package com.shianxian.trace.sys.dao;

import com.shianxian.trace.sys.pojo.RolePermission;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/19 11:15
 * @Description: 角色权限持久层接口
 */
@Repository
public interface RolePermissionDao extends Mapper<RolePermission> {
}
