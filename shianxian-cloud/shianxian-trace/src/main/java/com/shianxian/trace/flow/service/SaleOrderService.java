package com.shianxian.trace.flow.service;

import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.pojo.SaleOrder;
import com.shianxian.trace.flow.pojo.SaleOrderDetail;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/29 11:36
 * @Description: 销售单业务层接口
 */
public interface SaleOrderService {


    /**
     * 保存、修改销售单
     * @param saleOrder
     * @return
     */
    Integer saveOrUpdateSaleOrder(SaleOrder saleOrder);


    /**
     * 删除销售单
     * @param saleOrderId
     * @return
     */
    Integer deleteSaleOrder(Integer saleOrderId);


    /**
     * 根据企业id分页查询销售单
     * @param page
     * @param saleOrder
     * @return
     */
    Object selectSaleOrderByPage(Page page, SaleOrder saleOrder);


    /**
     * 根据销售单id分页查询销售单详情
     * @param page
     * @param saleOrderDetail
     * @return
     */
    Object selectSaleOrderDetailByPage(Page page, SaleOrderDetail saleOrderDetail);
}
