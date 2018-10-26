package com.shianxian.trace.flow.service;

import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.pojo.PurchaseInOrder;
import com.shianxian.trace.flow.pojo.PurchaseInOrderDetail;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/27 11:12
 * @Description: 采购入库单业务层接口
 */
public interface PurchaseInOrderService {


    /**
     * 保存、修改采购入库单
     * @param purchaseInOrder
     * @return
     */
    Integer saveOrUpdatePurchaseInOrder(PurchaseInOrder purchaseInOrder);


    /**
     * 删除采购入库单
     * @param purchaseInOrderId
     * @return
     */
    Integer deletePurchaseInOrder(Integer purchaseInOrderId);


    /**
     * 根据企业id分页查询采购入库单
     * @param page
     * @param purchaseInOrder
     * @return
     */
    Object selectPurchaseInOrderByPage(Page page, PurchaseInOrder purchaseInOrder);


    /**
     * 根据采购入库单id查询采购入库单详情
     * @param page
     * @param purchaseInOrderDetail
     * @return
     */
    Object selectPurchaseInOrderDetailByPage(Page page, PurchaseInOrderDetail purchaseInOrderDetail);


    /**
     * 根据企业id、物料id、物料类型查询库存
     * @param companyId
     * @param materialId
     * @param materialType
     * @return
     */
    Integer selectRepertory(Integer companyId, Integer materialId, Integer materialType);
}
