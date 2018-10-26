package com.shianxian.trace.flow.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.dao.MaterialDao;
import com.shianxian.trace.base.pojo.Material;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.dao.GoodsInOrderDao;
import com.shianxian.trace.flow.dao.ProcessMaterialDao;
import com.shianxian.trace.flow.dao.ProcessMaterialDetailDao;
import com.shianxian.trace.flow.dao.RawOutOrderDao;
import com.shianxian.trace.flow.pojo.GoodsInOrder;
import com.shianxian.trace.flow.pojo.ProcessMaterial;
import com.shianxian.trace.flow.pojo.ProcessMaterialDetail;
import com.shianxian.trace.flow.pojo.RawOutOrder;
import com.shianxian.trace.flow.service.ProcessMaterialService;
import com.shianxian.trace.flow.service.PurchaseInOrderService;
import com.shianxian.trace.flow.vo.ProcessMaterialDetailVO;
import com.shianxian.trace.flow.vo.ProcessMaterialVO;
import com.shianxian.trace.sys.pojo.User;
import com.shianxian.trace.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 11:05
 * @Description: 物料加工单业务层实现
 */
@Service
@Slf4j
public class ProcessMaterialServiceImpl implements ProcessMaterialService {


    @Autowired
    private ProcessMaterialDao processMaterialDao;

    @Autowired
    private ProcessMaterialDetailDao processMaterialDetailDao;

    @Autowired
    private RawOutOrderDao rawOutOrderDao;

    @Autowired
    private GoodsInOrderDao goodsInOrderDao;

    @Autowired
    private PurchaseInOrderService purchaseInOrderService;

    @Autowired
    private MaterialDao materialDao;


    /**
     *  添加物料加工单
     * @param processMaterial
     * @return
     */
    @Transactional
    @Override
    public Integer saveProcessMaterial(ProcessMaterial processMaterial) {
        // 先判断采购入库单库存是否够
        Integer count = this.purchaseInOrderService.selectRepertory(processMaterial.getCompanyId(), processMaterial.getMaterialId(), 1);
        if (count < processMaterial.getProcessMaterialNum()) {
            return 2;
        }
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        CommonUtils.setUserAndTime(processMaterial, loginUser);
        Integer flag = this.processMaterialDao.insertSelective(processMaterial);
        log.info("添加物料加工单成功！");
        if (flag != null && flag == 1) {
            // 保存原料出库单
            RawOutOrder rawOutOrder = new RawOutOrder();
            rawOutOrder.setCompanyId(processMaterial.getCompanyId());
            rawOutOrder.setRawOutOrderNo(CommonUtils.getOrderNo());
            rawOutOrder.setRawOutOrderTime(processMaterial.getCreateTime());
            rawOutOrder.setProcessMaterialId(processMaterial.getId());
            rawOutOrder.setProcessMaterialNo(processMaterial.getProcessMaterialNo());
            rawOutOrder.setProcessMaterialNum(processMaterial.getProcessMaterialNum());
            rawOutOrder.setMaterialId(processMaterial.getMaterialId());
            rawOutOrder.setCategoryId(processMaterial.getCategoryId());
            rawOutOrder.setUnitId(processMaterial.getUnitId());
            this.rawOutOrderDao.insertSelective(rawOutOrder);
            // 修改物料库存（原料）
            Material material = this.materialDao.selectByPrimaryKey(processMaterial.getMaterialId());
            if (material.getNum() == null) {
                material.setNum(0);
            }
            material.setNum(material.getNum() - processMaterial.getProcessMaterialNum());
            this.materialDao.updateByPrimaryKeySelective(material);
            log.info("添加原料出库单成功！");
            // 主表添加成功，循环添加物料加工单详情
            @Valid List<ProcessMaterialDetail> processMaterialDetails = processMaterial.getProcessMaterialDetails();
            for (ProcessMaterialDetail processMaterialDetail : processMaterialDetails) {
                processMaterialDetail.setProcessMaterialId(processMaterial.getId());
                this.processMaterialDetailDao.insertSelective(processMaterialDetail);
                log.info("添加物料加工单详情成功！");
                // 保存商品入库单
                GoodsInOrder goodsInOrder = new GoodsInOrder();
                goodsInOrder.setCompanyId(processMaterial.getCompanyId());
                goodsInOrder.setGoodsInOrderNo(CommonUtils.getOrderNo());
                goodsInOrder.setGoodsInOrderTime(processMaterial.getCreateTime());
                goodsInOrder.setProcessGoodsNum(processMaterialDetail.getProcessGoodsNum());
                goodsInOrder.setProcessMaterialId(processMaterial.getId());
                goodsInOrder.setProcessMaterialNo(processMaterial.getProcessMaterialNo());
                goodsInOrder.setMaterialId(processMaterialDetail.getMaterialId());
                goodsInOrder.setCategoryId(processMaterialDetail.getCategoryId());
                goodsInOrder.setUnitId(processMaterialDetail.getUnitId());
                this.goodsInOrderDao.insertSelective(goodsInOrder);
                log.info("添加商品入库单成功！");
                // 修改物料库存（商品）
                Material goodsMaterial = this.materialDao.selectByPrimaryKey(processMaterialDetail.getMaterialId());
                if (goodsMaterial.getNum() == null) {
                    goodsMaterial.setNum(0);
                }
                goodsMaterial.setNum(goodsMaterial.getNum() + processMaterialDetail.getProcessGoodsNum());
                this.materialDao.updateByPrimaryKeySelective(goodsMaterial);
            }
            return flag;
        }
        log.error("添加物料加工单错误！");
        throw new RuntimeException("添加物料加工单错误！");
    }


    /**
     * 删除物料加工单
     * @param processMaterialId
     * @return
     */
    @Transactional
    @Override
    public Integer deleteProcessMaterial(Integer processMaterialId) {
        // 删除物料加工单
        Integer flag = this.processMaterialDao.deleteByPrimaryKey(processMaterialId);
        if (flag != null && flag == 1) {
            // 级联删除物料加工单详情、原料出库单、商品入库单
            ProcessMaterialDetail processMaterialDetail = new ProcessMaterialDetail();
            processMaterialDetail.setProcessMaterialId(processMaterialId);
            this.processMaterialDetailDao.delete(processMaterialDetail);
            RawOutOrder rawOutOrder = new RawOutOrder();
            rawOutOrder.setProcessMaterialId(processMaterialId);
            this.rawOutOrderDao.delete(rawOutOrder);
            GoodsInOrder goodsInOrder = new GoodsInOrder();
            goodsInOrder.setProcessMaterialId(processMaterialId);
            this.goodsInOrderDao.delete(goodsInOrder);
            log.info("删除物料加工单、删除物料加工单详情、原料出库单、商品入库单成功！");
        }
        log.error("删除物料加工单错误！");
        throw new RuntimeException("删除物料加工单错误！");
    }


    /**
     * 根据企业id分页查询物料加工单
     * @param processMaterial
     * @return
     */
    @Override
    public Object selectProcessMaterialByPage(Page page, ProcessMaterial processMaterial) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<ProcessMaterialVO> processMaterialVOList = this.processMaterialDao.selectProcessMaterialByPage(processMaterial.getCompanyId());
        return ResultUtils.setData(objectPage.getTotal(), processMaterialVOList);
    }


    /**
     * 根据物料加工单id分页查询物料加工单详情
     * @param page
     * @param processMaterialDetail
     * @return
     */
    @Override
    public Object selectProcessMaterialDetailByPage(Page page, ProcessMaterialDetail processMaterialDetail) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<ProcessMaterialDetailVO> processMaterialDetailVOList = this.processMaterialDetailDao.selectProcessMaterialDetailByPage(processMaterialDetail.getProcessMaterialId());
        return ResultUtils.setData(objectPage.getTotal(), processMaterialDetailVOList);
    }
}
