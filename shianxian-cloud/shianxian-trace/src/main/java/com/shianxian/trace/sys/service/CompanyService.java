package com.shianxian.trace.sys.service;

import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.sys.pojo.Company;
import org.springframework.http.ResponseEntity;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/20 11:05
 * @Description: 企业业务层接口
 */
public interface CompanyService {


    /**
     * 保存或修改企业
     * @param company
     * @return
     */
    Integer saveOrUpdateCompany(Company company);


    /**
     * 根据用户id查询企业信息
     * @param userId
     * @return
     */
    ResponseEntity<Object> selectByUserId(Page page, Integer userId);


    /**
     * 删除企业
     * @param companyId
     * @return
     */
    Integer deleteCompany(Integer companyId);
}
