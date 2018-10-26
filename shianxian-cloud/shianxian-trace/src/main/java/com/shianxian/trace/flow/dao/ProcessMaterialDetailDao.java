package com.shianxian.trace.flow.dao;

import com.shianxian.trace.flow.pojo.ProcessMaterialDetail;
import com.shianxian.trace.flow.vo.ProcessMaterialDetailVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 10:49
 * @Description: 物料加工详情持久层接口
 */
@Repository
public interface ProcessMaterialDetailDao extends Mapper<ProcessMaterialDetail> {


    /**
     * 根据物料加工单id分页查询物料加工单详情
     * @param processMaterialId
     * @return
     */
    List<ProcessMaterialDetailVO> selectProcessMaterialDetailByPage(Integer processMaterialId);
}
