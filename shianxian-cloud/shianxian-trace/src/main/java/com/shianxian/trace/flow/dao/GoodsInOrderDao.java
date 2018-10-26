package com.shianxian.trace.flow.dao;

import com.shianxian.trace.flow.pojo.GoodsInOrder;
import com.shianxian.trace.flow.vo.GoodsInOrderVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 10:49
 * @Description: 商品入库单持久层接口
 */
@Repository
public interface GoodsInOrderDao extends Mapper<GoodsInOrder> {


    /**
     * 根据企业id分页查询商品入库单
     * @param companyId
     * @return
     */
    List<GoodsInOrderVO> selectgGodsInOrderByPage(Integer companyId);
}
