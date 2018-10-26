package com.shianxian.trace.flow.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.dao.MaterialDao;
import com.shianxian.trace.base.pojo.Material;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.dao.RawOutOrderDao;
import com.shianxian.trace.flow.pojo.RawOutOrder;
import com.shianxian.trace.flow.service.RawOutOrderService;
import com.shianxian.trace.flow.vo.RawOutOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 15:54
 * @Description: 原料出库单业务层实现
 */
@Service
public class RawOutOrderServiceImpl implements RawOutOrderService {


    @Autowired
    private RawOutOrderDao rawOutOrderDao;

    @Autowired
    private MaterialDao materialDao;


    /**
     * 根据企业id分页查询原料出库单
     * @param page
     * @param rawOutOrder
     * @return
     */
    @Override
    public Object selectRawOutOrderByPage(Page page, RawOutOrder rawOutOrder) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<RawOutOrderVO> rawOutOrderVOList = this.rawOutOrderDao.selectRawOutOrderByPage(rawOutOrder.getCompanyId());
        return ResultUtils.setData(objectPage.getTotal(), rawOutOrderVOList);
    }


    /**
     * 根据id改变原料出库单审核状态
     * @param rawOutOrder
     * @return
     */
    @Transactional
    @Override
    public Integer checkRawOutOrderById(RawOutOrder rawOutOrder) {
        if (rawOutOrder.getStatus() == 1 || rawOutOrder.getStatus() == 2) {
            // 当审核不过或待审核时将库存还原
            Material material = this.materialDao.selectByPrimaryKey(rawOutOrder.getMaterialId());
            if (material.getNum() == null) {
                material.setNum(0);
            }
            material.setNum(material.getNum() + rawOutOrder.getProcessMaterialNum());
            // 修改物料库存（原料）
            this.materialDao.updateByPrimaryKeySelective(material);
        }
        return this.rawOutOrderDao.updateByPrimaryKeySelective(rawOutOrder);
    }
}
