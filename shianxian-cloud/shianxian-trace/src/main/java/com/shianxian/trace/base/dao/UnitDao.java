package com.shianxian.trace.base.dao;

import com.shianxian.trace.base.pojo.Unit;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/25 11:22
 * @Description: 计量单位持久层接口
 */
@Repository
public interface UnitDao extends Mapper<Unit> {
}
