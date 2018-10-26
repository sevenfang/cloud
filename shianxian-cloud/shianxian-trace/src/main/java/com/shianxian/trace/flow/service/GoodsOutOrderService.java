package com.shianxian.trace.flow.service;

import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.pojo.GoodsOutOrder;
import com.shianxian.trace.flow.pojo.GoodsOutOrderDetail;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/29 15:01
 * @Description: 商品出库单业务层接口
 */
public interface GoodsOutOrderService {


    /**
     * 保存、修改商品出库单
     * @param goodsOutOrder
     * @return
     */
    Integer saveOrUpdateGoodsOutOrder(GoodsOutOrder goodsOutOrder);


    /**
     * 删除商品出库单
     * @param goodsOutOrderId
     * @return
     */
    Integer deleteGoodsOutOrder(Integer goodsOutOrderId);


    /**
     * 根据企业id查询商品出库单
     * @param page
     * @param goodsOutOrder
     * @return
     */
    Object selectGoodsOutOrderByPage(Page page, GoodsOutOrder goodsOutOrder);


    /**
     * 根据商品出库单id查询商品出库单详情
     * @param page
     * @param goodsOutOrderDetail
     * @return
     */
    Object selectGoodsOutOrderDetailByPage(Page page, GoodsOutOrderDetail goodsOutOrderDetail);
}
