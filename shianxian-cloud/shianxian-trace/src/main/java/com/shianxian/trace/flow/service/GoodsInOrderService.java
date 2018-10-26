package com.shianxian.trace.flow.service;

import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.pojo.GoodsInOrder;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 16:20
 * @Description: 商品入库单业务层接口
 */
public interface GoodsInOrderService {


    /**
     * 根据企业id分页查询商品入库单
     * @param page
     * @param goodsInOrder
     * @return
     */
    Object selectgGodsInOrderByPage(Page page, GoodsInOrder goodsInOrder);


    /**
     * 根据id改变商品入库单审核状态
     * @param goodsInOrder
     * @return
     */
    Integer checkGoodsInOrderById(GoodsInOrder goodsInOrder);
}
