package com.shianxian.trace.flow.service;

import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.pojo.ProcessMaterial;
import com.shianxian.trace.flow.pojo.ProcessMaterialDetail;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 11:05
 * @Description: 物料加工单业务层接口
 */
public interface ProcessMaterialService {


    /**
     *  添加物料加工单
     * @param processMaterial
     * @return
     */
    Integer saveProcessMaterial(ProcessMaterial processMaterial);


    /**
     * 删除物料加工单
     * @param processMaterialId
     * @return
     */
    Integer deleteProcessMaterial(Integer processMaterialId);


    /**
     * 根据企业id分页查询物料加工单
     * @param processMaterial
     * @return
     */
    Object selectProcessMaterialByPage(Page page, ProcessMaterial processMaterial);


    /**
     * 根据物料加工单id分页查询物料加工单详情
     * @param page
     * @param processMaterialDetail
     * @return
     */
    Object selectProcessMaterialDetailByPage(Page page, ProcessMaterialDetail processMaterialDetail);
}
