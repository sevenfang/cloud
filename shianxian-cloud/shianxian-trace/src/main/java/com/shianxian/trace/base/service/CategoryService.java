package com.shianxian.trace.base.service;

import com.shianxian.trace.base.pojo.Category;
import com.shianxian.trace.common.pojo.Page;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/25 10:33
 * @Description: 物料类别业务层接口
 */
public interface CategoryService {

    /**
     * 保存或修改物料类别
     * @param category
     * @return
     */
    Integer saveOrUpdateCategory(Category category);


    /**
     * 删除物料类别
     * @param categoryId
     * @return
     */
    Integer deleteCategory(Integer categoryId);


    /**
     * 根据企业id分页查询物料类别
     * @param page
     * @param category
     * @return
     */
    Object selectCategoryByPage(Page page, Category category);


    /**
     * 根据物料类别id查询物料类别
     * @param categoryId
     * @return
     */
    Category selectCategoryById(Integer categoryId);
}
