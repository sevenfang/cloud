package com.shianxian.trace.flow.dao;

import com.shianxian.trace.flow.pojo.GoodsOutOrder;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/29 14:21
 * @Description: 商品出库单持久层接口
 */
@Repository
public interface GoodsOutOrderDao extends Mapper<GoodsOutOrder> {
}
