package com.shianxian.trace.flow.dao;

import com.shianxian.trace.flow.pojo.SaleOrderDetail;
import com.shianxian.trace.flow.vo.SaleOrderDetailVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/29 11:37
 * @Description: 销售单详情持久层接口
 */
@Repository
public interface SaleOrderDetailDao extends Mapper<SaleOrderDetail> {


    /**
     * 根据销售单id分页查询销售单详情
     * @param saleOrderId
     * @return
     */
    List<SaleOrderDetailVO> selectSaleOrderDetailByPage(Integer saleOrderId);
}
