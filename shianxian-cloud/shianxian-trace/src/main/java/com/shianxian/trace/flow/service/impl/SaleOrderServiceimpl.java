package com.shianxian.trace.flow.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.dao.SaleOrderDao;
import com.shianxian.trace.flow.dao.SaleOrderDetailDao;
import com.shianxian.trace.flow.pojo.SaleOrder;
import com.shianxian.trace.flow.pojo.SaleOrderDetail;
import com.shianxian.trace.flow.service.SaleOrderService;
import com.shianxian.trace.flow.vo.SaleOrderDetailVO;
import com.shianxian.trace.sys.pojo.User;
import com.shianxian.trace.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/29 11:36
 * @Description: 销售单业务层实现
 */
@Service
@Slf4j
public class SaleOrderServiceimpl implements SaleOrderService {


    @Autowired
    private SaleOrderDao saleOrderDao;

    @Autowired
    private SaleOrderDetailDao saleOrderDetailDao;


    /**
     * 保存、修改销售单
     * @param saleOrder
     * @return
     */
    @Transactional
    @Override
    public Integer saveOrUpdateSaleOrder(SaleOrder saleOrder) {
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        @Valid List<SaleOrderDetail> saleOrderDetails = saleOrder.getSaleOrderDetails();
        if (saleOrder.getId() == null) {
            CommonUtils.setUserAndTime(saleOrder, loginUser);
            Integer flag = this.saleOrderDao.insertSelective(saleOrder);
            if (flag != null && flag == 1) {
                // 循环保存销售单详情
                for (SaleOrderDetail saleOrderDetail : saleOrderDetails) {
                    saleOrderDetail.setSaleOrderId(saleOrder.getId());
                    this.saleOrderDetailDao.insertSelective(saleOrderDetail);
                }
                return flag;
            }
        } else {
            CommonUtils.setUpdateUserAndUpdateTime(saleOrder, loginUser);
            Integer flag = this.saleOrderDao.updateByPrimaryKeySelective(saleOrder);
            if (flag != null && flag == 1) {
                // 循环修改销售单详情
                for (SaleOrderDetail saleOrderDetail : saleOrderDetails) {
                    this.saleOrderDetailDao.updateByPrimaryKeySelective(saleOrderDetail);
                }
                return flag;
            }
        }
        log.error("保存、修改销售单错误！");
        throw new RuntimeException("保存、修改销售单错误！");
    }


    /**
     * 删除销售单
     * @param saleOrderId
     * @return
     */
    @Transactional
    @Override
    public Integer deleteSaleOrder(Integer saleOrderId) {
        Integer flag = this.saleOrderDao.deleteByPrimaryKey(saleOrderId);
        if (flag != null && flag == 1) {
            // 级联删除销售单
            SaleOrderDetail saleOrderDetail = new SaleOrderDetail();
            saleOrderDetail.setSaleOrderId(saleOrderId);
            this.saleOrderDetailDao.delete(saleOrderDetail);
            return flag;
        }
        log.error("删除销售单错误！");
        throw new RuntimeException("删除销售单错误！");
    }


    /**
     * 根据企业id分页查询销售单
     * @param page
     * @param saleOrder
     * @return
     */
    @Override
    public Object selectSaleOrderByPage(Page page, SaleOrder saleOrder) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Example example = new Example(SaleOrder.class);
        example.setOrderByClause("update_time DESC, create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("companyId", saleOrder.getCompanyId());
        List<SaleOrder> saleOrderList = this.saleOrderDao.selectByExample(example);
        return ResultUtils.setData(objectPage.getTotal(), saleOrderList);
    }


    /**
     * 根据销售单id分页查询销售单详情
     * @param page
     * @param saleOrderDetail
     * @return
     */
    @Override
    public Object selectSaleOrderDetailByPage(Page page, SaleOrderDetail saleOrderDetail) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<SaleOrderDetailVO> saleOrderDetailVOList = this.saleOrderDetailDao.selectSaleOrderDetailByPage(saleOrderDetail.getSaleOrderId());
        return ResultUtils.setData(objectPage.getTotal(), saleOrderDetailVOList);
    }
}
