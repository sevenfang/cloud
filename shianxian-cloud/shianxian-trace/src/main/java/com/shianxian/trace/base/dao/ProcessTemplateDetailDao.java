package com.shianxian.trace.base.dao;

import com.shianxian.trace.base.pojo.ProcessTemplateDetail;
import com.shianxian.trace.base.vo.ProcessTemplateDetailVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/26 14:32
 * @Description: 加工模板详情
 */
@Repository
public interface ProcessTemplateDetailDao extends Mapper<ProcessTemplateDetail> {


    /**
     * 根据模板id分页查询模板详情
     * @param processTemplateId
     * @return
     */
    List<ProcessTemplateDetailVO> selectProcessTemplateItemByPage(Integer processTemplateId);
}
