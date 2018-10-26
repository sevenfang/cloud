package com.shianxian.trace.base.service;

import com.shianxian.trace.base.pojo.ProcessTemplate;
import com.shianxian.trace.base.pojo.ProcessTemplateDetail;
import com.shianxian.trace.common.pojo.Page;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/26 14:21
 * @Description: 加工模板业务层接口
 */
public interface ProcessTemplateService {


    /**
     * 保存、修改加工模板
     *
     * @param processTemplate
     * @return
     */
    Integer saveOrUpdateProcessTemplate(ProcessTemplate processTemplate);


    /**
     * 删除加工模板
     * @param processTemplateId
     * @return
     */
    Integer deleteProcessTemplate(Integer processTemplateId);


    /**
     * 根据企业id分页查询模板
     * @param page
     * @param processTemplate
     * @return
     */
    Object selectProcessTemplateByPage(Page page, ProcessTemplate processTemplate);


    /**
     * 根据模板id分页查询模板详情
     * @param page
     * @param processTemplateDetail
     * @return
     */
    Object selectProcessTemplateItemByPage(Page page, ProcessTemplateDetail processTemplateDetail);
}
