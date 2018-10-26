package com.shianxian.trace.sys.dao;

import com.shianxian.trace.sys.pojo.Company;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/20 11:08
 * @Description: 企业持久层接口
 */
@Repository
public interface CompanyDao extends Mapper<Company> {
}
