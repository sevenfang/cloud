package com.shianxian.trace.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.dao.CustomerDao;
import com.shianxian.trace.base.pojo.Customer;
import com.shianxian.trace.base.service.CustomerService;
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
 * @Date: 2018/9/26 11:31
 * @Description: 客户业务层实现
 */
@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerDao customerDao;


    /**
     * 保存、修改客户
     * @param customer
     * @return
     */
    @Transactional
    @Override
    public Integer saveOrUpdateCustomer(Customer customer) {
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        if (customer.getId() == null) {
            CommonUtils.setUserAndTime(customer, loginUser);
            return this.customerDao.insertSelective(customer);
        } else {
            CommonUtils.setUpdateUserAndUpdateTime(customer, loginUser);
            return this.customerDao.updateByPrimaryKeySelective(customer);
        }
    }


    /**
     * 删除客户
     * @param customerId
     * @return
     */
    @Transactional
    @Override
    public Integer deleteCustomer(Integer customerId) {
        return this.customerDao.deleteByPrimaryKey(customerId);
    }


    /**
     * 根据企业id分页查询客户
     * @param page
     * @param customer
     * @return
     */
    @Override
    public Object selectCustomerByPage(Page page, Customer customer) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Example example = new Example(Customer.class);
        example.setOrderByClause("update_time DESC, create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("companyId", customer.getCompanyId());
        List<Customer> customerList = this.customerDao.selectByExample(example);
        return ResultUtils.setData(objectPage.getTotal(), customerList);
    }
}
