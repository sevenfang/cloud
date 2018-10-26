package com.shianxian.trace.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.dao.UnitDao;
import com.shianxian.trace.base.pojo.Unit;
import com.shianxian.trace.base.service.UnitService;
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
 * @Date: 2018/9/25 11:20
 * @Description: 计量单位业务层实现
 */
@Service
public class UnitServiceImpl implements UnitService {


    @Autowired
    private UnitDao unitDao;


    /**
     * 保存、修改计量单位
     * @param unit
     * @return
     */
    @Transactional
    @Override
    public Integer saveOrUpdateUnit(Unit unit) {
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        if (unit.getId() == null) {
            CommonUtils.setUserAndTime(unit, loginUser);
            return this.unitDao.insertSelective(unit);
        } else {
            CommonUtils.setUpdateUserAndUpdateTime(unit, loginUser);
            return this.unitDao.updateByPrimaryKeySelective(unit);
        }
    }


    /**
     * 删除计量单位
     * @param unitId
     * @return
     */
    @Override
    public Integer deleteUnit(Integer unitId) {
        return this.unitDao.deleteByPrimaryKey(unitId);
    }


    /**
     * 根据企业id分页查询计量单位
     * @param page
     * @param unit
     * @return
     */
    @Override
    public Object selectUnitByPage(Page page, Unit unit) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Example example = new Example(Unit.class);
        example.setOrderByClause("update_time DESC, create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("companyId", unit.getCompanyId());
        List<Unit> unitList = this.unitDao.selectByExample(example);
        return ResultUtils.setData(objectPage.getTotal(), unitList);
    }


    /**
     * 根据计量单位id查询计量单位
     * @param unitId
     * @return
     */
    @Override
    public Unit selectUnitById(Integer unitId) {
        return this.unitDao.selectByPrimaryKey(unitId);
    }
}
