package com.shianxian.trace.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.dao.MaterialDao;
import com.shianxian.trace.base.pojo.Material;
import com.shianxian.trace.base.service.MaterialService;
import com.shianxian.trace.base.vo.MaterialVO;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.sys.pojo.User;
import com.shianxian.trace.utils.CommonUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/25 13:46
 * @Description: 物料业务层实现
 */
@Service
public class MaterialServiceImpl implements MaterialService {


    @Autowired
    private MaterialDao materialDao;


    /**
     * 保存、修改物料
     * @param material
     * @return
     */
    @Override
    @Transactional
    public Integer saveOrUpdateMaterial(Material material) {
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        if (material.getId() == null) {
            CommonUtils.setUserAndTime(material, loginUser);
            return this.materialDao.insertSelective(material);
        } else {
            CommonUtils.setUpdateUserAndUpdateTime(material, loginUser);
            return this.materialDao.updateByPrimaryKeySelective(material);
        }
    }


    /**
     * 删除物料
     * @param materialId
     * @return
     */
    @Transactional
    @Override
    public Integer deleteMaterial(Integer materialId) {
        return this.materialDao.deleteByPrimaryKey(materialId);
    }


    /**
     * 根据企业id、物料类型分页查询物料
     * @param page
     * @param material
     * @return
     */
    @Override
    public Object selectMaterialByPage(Page page, Material material) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<MaterialVO> materialVOList = this.materialDao.selectMaterialByPage(material);
        return ResultUtils.setData(objectPage.getTotal(), materialVOList);
    }


    /**
     * 根据物料id查询物料
     * @param materiaId
     * @return
     */
    @Override
    public Material selectMaterialById(Integer materiaId) {
        return this.materialDao.selectByPrimaryKey(materiaId);
    }
}
