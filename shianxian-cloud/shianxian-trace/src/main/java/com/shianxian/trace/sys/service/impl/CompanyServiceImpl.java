package com.shianxian.trace.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.sys.dao.CompanyDao;
import com.shianxian.trace.sys.dao.UserDao;
import com.shianxian.trace.sys.pojo.Company;
import com.shianxian.trace.sys.pojo.User;
import com.shianxian.trace.sys.service.CompanyService;
import com.shianxian.trace.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/20 11:07
 * @Description: 企业业务层实现
 */
@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {


    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private UserDao userDao;


    /**
     * 保存或修改企业
     * @param company
     * @return
     */
    @Override
    @Transactional
    public Integer saveOrUpdateCompany(Company company) {
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        if (company.getId() == null) {
            // 保存
            log.info("保存企业");
            CommonUtils.setUserAndTime(company, loginUser);
            return this.companyDao.insertSelective(company);
        } else {
            // 修改
            log.info("修改企业");
            CommonUtils.setUpdateUserAndUpdateTime(company, loginUser);
            return this.companyDao.updateByPrimaryKeySelective(company);
        }
    }


    /**
     * 根据用户id查询企业信息
     * @return
     */
    @Override
    public ResponseEntity<Object> selectByUserId(Page page, Integer userId) {
        List<Company> companyList = new ArrayList<>();;
        User user = this.userDao.selectByPrimaryKey(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResultUtils.setMsg("没有这个用户！"));
        }
        // 分页
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        // 判断是否是管理员，如果是管理员，则查询所有企业。
        if (user.getIsAdmin() == 1 || user.getIsAdmin() == 2) {
            Example example = new Example(Company.class);
            example.setOrderByClause("update_time DESC, create_time DESC");
            companyList = this.companyDao.selectByExample(example);
        } else {
            Company company = this.companyDao.selectByPrimaryKey(user.getCompanyId());
            companyList.add(company);
        }
        return ResponseEntity.ok(ResultUtils.setData(objectPage.getTotal(), companyList));
    }


    /**
     * 删除企业
     * @param companyId
     * @return
     */
    @Override
    public Integer deleteCompany(Integer companyId) {
        return this.companyDao.deleteByPrimaryKey(companyId);
    }


}
