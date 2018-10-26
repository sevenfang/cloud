package com.shianxian.trace.base.service;

import com.shianxian.trace.base.pojo.Unit;
import com.shianxian.trace.common.pojo.Page;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/25 11:19
 * @Description: 计量单位业务层接口
 */
public interface UnitService {

    /**
     * 保存、修改计量单位
     * @param unit
     * @return
     */
    Integer saveOrUpdateUnit(Unit unit);


    /**
     * 删除计量单位
     * @param unitId
     * @return
     */
    Integer deleteUnit(Integer unitId);


    /**
     * 根据企业id分页查询计量单位
     * @param page
     * @param unit
     * @return
     */
    Object selectUnitByPage(Page page, Unit unit);


    /**
     * 根据计量单位id查询计量单位
     * @param unitId
     * @return
     */
    Unit selectUnitById(Integer unitId);
}
