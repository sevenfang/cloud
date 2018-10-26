package com.shianxian.trace.flow.dao;

import com.shianxian.trace.flow.pojo.PurchaseInOrder;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/27 11:16
 * @Description: 采购入库单持久层接口
 */
@Repository
public interface PurchaseInOrderDao extends Mapper<PurchaseInOrder> {
}
