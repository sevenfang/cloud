package com.shianxian.trace.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.dao.SupplierDao;
import com.shianxian.trace.base.pojo.Supplier;
import com.shianxian.trace.base.service.SupplierService;
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
 * @Date: 2018/9/26 10:50
 * @Description: 供应商业务层实现
 */
@Service
public class SupplierServiceImpl implements SupplierService {


    @Autowired
    private SupplierDao supplierDao;


    /**
     * 保存、修改供应商
     * @param supplier
     * @return
     */
    @Transactional
    @Override
    public Integer saveOrUpdateSupplier(Supplier supplier) {
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        if (supplier.getId() == null) {
            CommonUtils.setUserAndTime(supplier, loginUser);
            return this.supplierDao.insertSelective(supplier);
        } else {
            CommonUtils.setUpdateUserAndUpdateTime(supplier, loginUser);
            return this.supplierDao.updateByPrimaryKeySelective(supplier);
        }
    }


    /**
     * 删除供应商
     * @param supplierId
     * @return
     */
    @Transactional
    @Override
    public Integer deleteSupplier(Integer supplierId) {
        return this.supplierDao.deleteByPrimaryKey(supplierId);
    }


    /**
     * 根据企业id分页查询供应商
     * @param supplier
     * @return
     */
    @Override
    public Object selectSupplierByPage(Page page, Supplier supplier) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Example example = new Example(Supplier.class);
        example.setOrderByClause("update_time DESC, create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("companyId", supplier.getCompanyId());
        List<Supplier> supplierList = this.supplierDao.selectByExample(example);
        return ResultUtils.setData(objectPage.getTotal(), supplierList);
    }
}
