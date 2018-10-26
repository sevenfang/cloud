package com.shianxian.trace.flow.dao;

import com.shianxian.trace.flow.pojo.SaleOrder;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/29 11:37
 * @Description: 销售单持久层接口
 */
@Repository
public interface SaleOrderDao extends Mapper<SaleOrder> {
}
