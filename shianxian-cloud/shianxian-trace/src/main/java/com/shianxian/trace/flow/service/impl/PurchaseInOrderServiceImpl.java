package com.shianxian.trace.flow.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.dao.MaterialDao;
import com.shianxian.trace.base.pojo.Material;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.dao.PurchaseInOrderDao;
import com.shianxian.trace.flow.dao.PurchaseInOrderDetailDao;
import com.shianxian.trace.flow.pojo.PurchaseInOrder;
import com.shianxian.trace.flow.pojo.PurchaseInOrderDetail;
import com.shianxian.trace.flow.service.PurchaseInOrderService;
import com.shianxian.trace.flow.vo.PurchaseInOrderDetailVO;
import com.shianxian.trace.sys.pojo.User;
import com.shianxian.trace.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/27 11:13
 * @Description: 采购入库单业务层实现
 */
@Service
@Slf4j
public class PurchaseInOrderServiceImpl implements PurchaseInOrderService {


    @Autowired
    private PurchaseInOrderDao purchaseInOrderDao;

    @Autowired
    private PurchaseInOrderDetailDao purchaseInOrderDetailDao;

    @Autowired
    private MaterialDao materialDao;


    /**
     * 保存、修改采购入库单
     * @param purchaseInOrder
     * @return
     */
    @Override
    @Transactional
    public Integer saveOrUpdatePurchaseInOrder(PurchaseInOrder purchaseInOrder) {
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        @Valid List<PurchaseInOrderDetail> purchaseInOrderDetails = purchaseInOrder.getPurchaseInOrderDetails();
        if (purchaseInOrder.getId() == null) {
            // 保存
            CommonUtils.setUserAndTime(purchaseInOrder, loginUser);
            Integer flag = this.purchaseInOrderDao.insertSelective(purchaseInOrder);
            if (flag != null && flag == 1) {
                // 循环批量保存采购入库单详情
                for (PurchaseInOrderDetail purchaseInOrderDetail : purchaseInOrderDetails) {
                    purchaseInOrderDetail.setPurchaseInOrderId(purchaseInOrder.getId());
                    this.purchaseInOrderDetailDao.insertSelective(purchaseInOrderDetail);
                    // 增加物料库存
                    Material material = this.materialDao.selectByPrimaryKey(purchaseInOrderDetail.getMaterialId());
                    if (material.getNum() == null) {
                        material.setNum(0);
                    }
                    material.setNum(material.getNum() + purchaseInOrderDetail.getPurchaseInOrderNum());
                    this.materialDao.updateByPrimaryKeySelective(material);
                }
                return flag;
            }
            log.error("保存、修改采购入库单错误！");
            throw new RuntimeException("保存、修改采购入库单错误！");
        } else {
            // 修改
            CommonUtils.setUpdateUserAndUpdateTime(purchaseInOrder, loginUser);
            Integer flag = this.purchaseInOrderDao.updateByPrimaryKeySelective(purchaseInOrder);
            if (flag != null && flag == 1) {
                // 循环批量保存采购入库单详情
                for (PurchaseInOrderDetail purchaseInOrderDetail : purchaseInOrderDetails) {
                    purchaseInOrderDetail.setPurchaseInOrderId(purchaseInOrder.getId());
                    this.purchaseInOrderDetailDao.updateByPrimaryKeySelective(purchaseInOrderDetail);
                    // 增加物料库存
                    Material material = this.materialDao.selectByPrimaryKey(purchaseInOrderDetail.getMaterialId());
                    if (material.getNum() == null) {
                        material.setNum(0);
                    }
                    material.setNum(material.getNum() + purchaseInOrderDetail.getPurchaseInOrderNum());
                    this.materialDao.updateByPrimaryKeySelective(material);
                }
                return flag;
            }
            log.error("保存、修改采购入库单错误！");
            throw new RuntimeException("保存、修改采购入库单错误！");
        }
    }


    /**
     * 删除采购入库单
     * @param purchaseInOrderId
     * @return
     */
    @Transactional
    @Override
    public Integer deletePurchaseInOrder(Integer purchaseInOrderId) {
        Integer flag = this.purchaseInOrderDao.deleteByPrimaryKey(purchaseInOrderId);
        if (flag != null && flag == 1) {
            PurchaseInOrderDetail purchaseInOrderDetail = new PurchaseInOrderDetail();
            purchaseInOrderDetail.setPurchaseInOrderId(purchaseInOrderId);
            this.purchaseInOrderDetailDao.delete(purchaseInOrderDetail);
            return flag;
        }
        log.error("删除采购入库单错误！");
        throw new RuntimeException("删除采购入库单错误！");
    }


    /**
     * 根据企业id分页查询采购入库单
     * @param page
     * @param purchaseInOrder
     * @return
     */
    @Override
    public Object selectPurchaseInOrderByPage(Page page, PurchaseInOrder purchaseInOrder) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Example example = new Example(PurchaseInOrder.class);
        example.setOrderByClause("update_time DESC, create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("companyId", purchaseInOrder.getCompanyId());
        List<PurchaseInOrder> purchaseInOrderList = this.purchaseInOrderDao.selectByExample(example);
        return ResultUtils.setData(objectPage.getTotal(), purchaseInOrderList);
    }


    /**
     * 根据采购入库单id查询采购入库单详情
     * @param page
     * @param purchaseInOrderDetail
     * @return
     */
    @Override
    public Object selectPurchaseInOrderDetailByPage(Page page, PurchaseInOrderDetail purchaseInOrderDetail) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<PurchaseInOrderDetailVO> purchaseInOrderDetailVOList = this.purchaseInOrderDetailDao.selectPurchaseInOrderDetailByPage(purchaseInOrderDetail.getPurchaseInOrderId());
        return ResultUtils.setData(objectPage.getTotal(), purchaseInOrderDetailVOList);
    }


    /**
     * 根据企业id、物料id、物料类型查询库存
     * @param companyId
     * @param materialId
     * @param materialType
     * @return
     */
    @Override
    public Integer selectRepertory(Integer companyId, Integer materialId, Integer materialType) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("companyId", companyId);
        queryMap.put("materialId", materialId);
        queryMap.put("materialType", materialType);
        return this.purchaseInOrderDetailDao.selectRepertory(queryMap);
    }
}
