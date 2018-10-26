package com.shianxian.trace.flow.dao;

import com.shianxian.trace.flow.pojo.GoodsOutOrderDetail;
import com.shianxian.trace.flow.vo.GoodsOutOrderDetailVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/29 14:21
 * @Description: 商品出库单详情持久层接口
 */
@Repository
public interface GoodsOutOrderDetailDao extends Mapper<GoodsOutOrderDetail> {


    /**
     * 根据商品出库单id查询商品出库单详情
     * @param goodsOutOrderId
     * @return
     */
    List<GoodsOutOrderDetailVO> selectGoodsOutOrderDetailByPage(Integer goodsOutOrderId);
}
