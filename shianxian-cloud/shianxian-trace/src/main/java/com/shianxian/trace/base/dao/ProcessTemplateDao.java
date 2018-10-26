package com.shianxian.trace.base.dao;

import com.shianxian.trace.base.pojo.ProcessTemplate;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/26 14:23
 * @Description: 加工模板持久层接口
 */
@Repository
public interface ProcessTemplateDao extends Mapper<ProcessTemplate> {
}
