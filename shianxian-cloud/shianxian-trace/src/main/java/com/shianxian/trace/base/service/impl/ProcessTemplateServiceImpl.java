package com.shianxian.trace.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.dao.ProcessTemplateDao;
import com.shianxian.trace.base.dao.ProcessTemplateDetailDao;
import com.shianxian.trace.base.pojo.ProcessTemplate;
import com.shianxian.trace.base.pojo.ProcessTemplateDetail;
import com.shianxian.trace.base.service.ProcessTemplateService;
import com.shianxian.trace.base.vo.ProcessTemplateDetailVO;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.sys.pojo.User;
import com.shianxian.trace.utils.CommonUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/26 14:22
 * @Description: 加工模板业务层实现
 */
@Service
public class ProcessTemplateServiceImpl implements ProcessTemplateService {


    @Autowired
    private ProcessTemplateDao processTemplateDao;


    @Autowired
    private ProcessTemplateDetailDao processTemplateDetailDao;


    /**
     * 保存、修改加工模板
     *
     * @param processTemplate
     * @return
     */
    @Transactional
    @Override
    public Integer saveOrUpdateProcessTemplate(ProcessTemplate processTemplate) {
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        @Valid List<ProcessTemplateDetail> processTemplateDetails = processTemplate.getProcessTemplateDetails();
        if (processTemplate.getId() == null) {
            CommonUtils.setUserAndTime(processTemplate, loginUser);
            // 保存模板
            Integer flag = this.processTemplateDao.insertSelective(processTemplate);
            if (flag != null && flag == 1) {
                // 批量保存模板详情
                for (ProcessTemplateDetail processTemplateDetail : processTemplateDetails) {
                    processTemplateDetail.setProcessTemplateId(processTemplate.getId());
                    this.processTemplateDetailDao.insertSelective(processTemplateDetail);
                }
                return flag;
            }
            throw new RuntimeException("保存加工模板主表错误！");
        } else {
            CommonUtils.setUpdateUserAndUpdateTime(processTemplate, loginUser);
            // 修改模板
            Integer flag = this.processTemplateDao.updateByPrimaryKeySelective(processTemplate);
            if (flag != null && flag == 1) {
                // 循环修改模板详情
                for (ProcessTemplateDetail processTemplateDetail : processTemplateDetails) {
                    if (processTemplateDetail.getId() == null) {
                        throw new RuntimeException("模板详情没有id，没有模板详情id的物料为：" + processTemplateDetail.getMaterialId());
                    }
                    this.processTemplateDetailDao.updateByPrimaryKeySelective(processTemplateDetail);
                }
                return flag;
            }
            throw new RuntimeException("修改加工模板主表错误！");
        }
    }


    /**
     * 删除加工模板
     *
     * @param processTemplateId
     * @return
     */
    @Transactional
    @Override
    public Integer deleteProcessTemplate(Integer processTemplateId) {
        int flag = this.processTemplateDao.deleteByPrimaryKey(processTemplateId);
        // 级联删除加工模板详情
        if (flag == 1) {
            ProcessTemplateDetail deleteTemplate = new ProcessTemplateDetail();
            deleteTemplate.setProcessTemplateId(processTemplateId);
            this.processTemplateDetailDao.delete(deleteTemplate);
            return flag;
        }
        throw new RuntimeException("删除加工模板错误！");
    }


    /**
     * 根据企业id分页查询模板
     *
     * @param page
     * @param processTemplate
     * @return
     */
    @Override
    public Object selectProcessTemplateByPage(Page page, ProcessTemplate processTemplate) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Example example = new Example(ProcessTemplate.class);
        example.setOrderByClause("update_time DESC, create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("companyId", processTemplate.getCompanyId());
        List<ProcessTemplate> processTemplateList = this.processTemplateDao.selectByExample(example);
        return ResultUtils.setData(objectPage.getTotal(), processTemplateList);
    }


    /**
     * 根据模板id分页查询模板详情
     *
     * @param page
     * @param processTemplateDetail
     * @return
     */
    @Override
    public Object selectProcessTemplateItemByPage(Page page, ProcessTemplateDetail processTemplateDetail) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<ProcessTemplateDetailVO> processTemplateDetailVOList = this.processTemplateDetailDao.selectProcessTemplateItemByPage(processTemplateDetail.getProcessTemplateId());
        return ResultUtils.setData(objectPage.getTotal(), processTemplateDetailVOList);
    }
}
