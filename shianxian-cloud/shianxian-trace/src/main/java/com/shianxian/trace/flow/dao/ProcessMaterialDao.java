package com.shianxian.trace.flow.dao;

import com.shianxian.trace.flow.pojo.ProcessMaterial;
import com.shianxian.trace.flow.vo.ProcessMaterialVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 10:49
 * @Description: 物料加工单持久层接口
 */
@Repository
public interface ProcessMaterialDao extends Mapper<ProcessMaterial> {


    /**
     * 根据企业id分页查询物料加工单
     * @param companyId
     * @return
     */
    List<ProcessMaterialVO> selectProcessMaterialByPage(Integer companyId);
}
