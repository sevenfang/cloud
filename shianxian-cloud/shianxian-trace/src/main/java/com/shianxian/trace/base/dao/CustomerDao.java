package com.shianxian.trace.base.dao;

import com.shianxian.trace.base.pojo.Customer;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/26 11:32
 * @Description: 客户持久层接口
 */
@Repository
public interface CustomerDao extends Mapper<Customer> {
}
