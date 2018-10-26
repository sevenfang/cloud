package com.shianxian.trace.flow.service;

import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.pojo.RawOutOrder;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 15:54
 * @Description: 原料出库单业务层接口
 */
public interface RawOutOrderService {


    /**
     * 根据企业id分页查询原料出库单
     * @param page
     * @param rawOutOrder
     * @return
     */
    Object selectRawOutOrderByPage(Page page, RawOutOrder rawOutOrder);


    /**
     * 根据id改变原料出库单审核状态
     * @param rawOutOrder
     * @return
     */
    Integer checkRawOutOrderById(RawOutOrder rawOutOrder);
}
