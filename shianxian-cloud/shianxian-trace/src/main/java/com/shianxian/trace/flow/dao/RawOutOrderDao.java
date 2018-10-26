package com.shianxian.trace.flow.dao;

import com.shianxian.trace.flow.pojo.RawOutOrder;
import com.shianxian.trace.flow.vo.RawOutOrderVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 10:49
 * @Description: 原料出库单单持久层接口
 */
@Repository
public interface RawOutOrderDao extends Mapper<RawOutOrder> {


    /**
     * 根据企业id分页查询原料出库单
     * @param companyId
     * @return
     */
    List<RawOutOrderVO> selectRawOutOrderByPage(Integer companyId);
}
