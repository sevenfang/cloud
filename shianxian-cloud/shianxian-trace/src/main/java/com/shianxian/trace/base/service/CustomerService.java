package com.shianxian.trace.base.service;

import com.shianxian.trace.base.pojo.Customer;
import com.shianxian.trace.common.pojo.Page;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/26 11:30
 * @Description: 客户业务层接口
 */
public interface CustomerService {


    /**
     * 保存、修改客户
     * @param customer
     * @return
     */
    Integer saveOrUpdateCustomer(Customer customer);


    /**
     * 删除客户
     * @param customerId
     * @return
     */
    Integer deleteCustomer(Integer customerId);


    /**
     * 根据企业id分页查询客户
     * @param page
     * @param customer
     * @return
     */
    Object selectCustomerByPage(Page page, Customer customer);
}
