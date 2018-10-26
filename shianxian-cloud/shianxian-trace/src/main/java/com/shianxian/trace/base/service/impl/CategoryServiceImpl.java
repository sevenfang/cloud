package com.shianxian.trace.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.dao.CategoryDao;
import com.shianxian.trace.base.pojo.Category;
import com.shianxian.trace.base.service.CategoryService;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.sys.pojo.User;
import com.shianxian.trace.utils.CommonUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/25 10:33
 * @Description: 物料类别业务层实现
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryDao categoryDao;

    /**
     * 保存或修改物料类别
     * @param category
     * @return
     */
    @Transactional
    @Override
    public Integer saveOrUpdateCategory(Category category) {
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        if (category.getId() == null) {
            CommonUtils.setUserAndTime(category, loginUser);
            return this.categoryDao.insertSelective(category);
        } else {
            CommonUtils.setUpdateUserAndUpdateTime(category, loginUser);
            return this.categoryDao.updateByPrimaryKeySelective(category);
        }
    }


    /**
     * 删除物料类别
     * @param categoryId
     * @return
     */
    @Transactional
    @Override
    public Integer deleteCategory(Integer categoryId) {
        return this.categoryDao.deleteByPrimaryKey(categoryId);
    }


    /**
     * 根据企业id分页查询物料类别
     * @param page
     * @param category
     * @return
     */
    @Override
    public Object selectCategoryByPage(Page page, Category category) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Example example = new Example(Category.class);
        example.setOrderByClause("update_time DESC, create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("companyId", category.getCompanyId());
        if (category.getCategoryType() != null) {
            criteria.andEqualTo("categoryType", category.getCategoryType());
        }
        List<Category> categoryList = this.categoryDao.selectByExample(example);
        return ResultUtils.setData(objectPage.getTotal(), categoryList);
    }


    /**
     * 根据物料类别id查询物料类别
     * @param categoryId
     * @return
     */
    @Override
    public Category selectCategoryById(Integer categoryId) {
        return this.categoryDao.selectByPrimaryKey(categoryId);
    }
}
