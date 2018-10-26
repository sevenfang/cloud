package com.shianxian.trace.flow.dao;

import com.shianxian.trace.flow.pojo.PurchaseInOrderDetail;
import com.shianxian.trace.flow.vo.PurchaseInOrderDetailVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/27 11:18
 * @Description: 采购入库单详情持久层接口
 */
@Repository
public interface PurchaseInOrderDetailDao extends Mapper<PurchaseInOrderDetail> {


    /**
     * 根据采购入库单id查询采购入库单详情
     * @param purchaseInOrderId
     * @return
     */
    List<PurchaseInOrderDetailVO> selectPurchaseInOrderDetailByPage(Integer purchaseInOrderId);


    /**
     * 根据企业id、物料id、物料类型查询库存
     * @param queryMap
     * @return
     */
    Integer selectRepertory(Map<String, Object> queryMap);
}
