package com.shianxian.trace.base.dao;

import com.shianxian.trace.base.pojo.Supplier;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/26 10:52
 * @Description: 供应商持久层接口
 */
@Repository
public interface SupplierDao extends Mapper<Supplier> {
}
