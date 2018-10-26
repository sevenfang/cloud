package com.shianxian.trace.base.service;

import com.shianxian.trace.base.pojo.Supplier;
import com.shianxian.trace.common.pojo.Page;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/26 10:50
 * @Description: 供应商业务层接口
 */
public interface SupplierService {


    /**
     * 保存、修改供应商
     * @param supplier
     * @return
     */
    Integer saveOrUpdateSupplier(Supplier supplier);


    /**
     * 删除供应商
     * @param supplierId
     * @return
     */
    Integer deleteSupplier(Integer supplierId);


    /**
     * 根据企业id分页查询供应商
     * @param supplier
     * @return
     */
    Object selectSupplierByPage(Page page, Supplier supplier);
}
