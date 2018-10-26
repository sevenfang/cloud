package com.shianxian.trace.base.service;

import com.shianxian.trace.base.pojo.Material;
import com.shianxian.trace.common.pojo.Page;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/25 13:45
 * @Description: 物料业务层接口
 */
public interface MaterialService {

    /**
     * 保存、修改物料
     * @param material
     * @return
     */
    Integer saveOrUpdateMaterial(Material material);


    /**
     * 删除物料
     * @param materialId
     * @return
     */
    Integer deleteMaterial(Integer materialId);


    /**
     * 根据企业id、物料类型分页查询物料
     * @param page
     * @param material
     * @return
     */
    Object selectMaterialByPage(Page page, Material material);


    /**
     * 根据物料id查询物料
     * @param materiaId
     * @return
     */
    Material selectMaterialById(Integer materiaId);
}
