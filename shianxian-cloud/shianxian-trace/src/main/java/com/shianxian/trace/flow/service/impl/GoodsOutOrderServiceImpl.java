package com.shianxian.trace.flow.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.dao.MaterialDao;
import com.shianxian.trace.base.pojo.Material;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.dao.GoodsOutOrderDao;
import com.shianxian.trace.flow.dao.GoodsOutOrderDetailDao;
import com.shianxian.trace.flow.pojo.GoodsOutOrder;
import com.shianxian.trace.flow.pojo.GoodsOutOrderDetail;
import com.shianxian.trace.flow.service.GoodsOutOrderService;
import com.shianxian.trace.flow.vo.GoodsOutOrderDetailVO;
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
 * @Date: 2018/9/29 15:01
 * @Description: 商品出库单详情
 */
@Service
@Slf4j
public class GoodsOutOrderServiceImpl implements GoodsOutOrderService {


    @Autowired
    private GoodsOutOrderDao goodsOutOrderDao;

    @Autowired
    private GoodsOutOrderDetailDao goodsOutOrderDetailDao;

    @Autowired
    private MaterialDao materialDao;


    /**
     * 保存、修改商品出库单
     * @param goodsOutOrder
     * @return
     */
    @Transactional
    @Override
    public Integer saveOrUpdateGoodsOutOrder(GoodsOutOrder goodsOutOrder) {
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        @Valid List<GoodsOutOrderDetail> goodsOutOrderDetails = goodsOutOrder.getGoodsOutOrderDetails();
        if (goodsOutOrder.getId() == null) {
            CommonUtils.setUserAndTime(goodsOutOrder, loginUser);
            Integer flag = this.goodsOutOrderDao.insertSelective(goodsOutOrder);
            if (flag != null && flag == 1) {
                for (GoodsOutOrderDetail goodsOutOrderDetail : goodsOutOrderDetails) {
                    goodsOutOrderDetail.setGoodsOutOrderId(goodsOutOrder.getId());
                    this.goodsOutOrderDetailDao.insertSelective(goodsOutOrderDetail);
                    // 修改物料库存（商品）
                    Material material = this.materialDao.selectByPrimaryKey(goodsOutOrderDetail.getMaterialId());
                    if (material.getNum() == null) {
                        material.setNum(0);
                    }
                    material.setNum(material.getNum() - goodsOutOrderDetail.getGoodsNum());
                    this.materialDao.updateByPrimaryKeySelective(material);
                }
                return flag;
            }
        } else {
            CommonUtils.setUpdateUserAndUpdateTime(goodsOutOrder, loginUser);
            Integer flag = this.goodsOutOrderDao.updateByPrimaryKeySelective(goodsOutOrder);
            if (flag != null && flag == 1) {
                for (GoodsOutOrderDetail goodsOutOrderDetail : goodsOutOrderDetails) {
                    this.goodsOutOrderDetailDao.updateByPrimaryKeySelective(goodsOutOrderDetail);
                }
                return flag;
            }
        }
        log.error("保存、修改商品出库单错误！");
        throw new RuntimeException("保存、修改商品出库单错误！");
    }


    /**
     * 删除商品出库单
     * @param goodsOutOrderId
     * @return
     */
    @Transactional
    @Override
    public Integer deleteGoodsOutOrder(Integer goodsOutOrderId) {
        Integer flag = this.goodsOutOrderDao.deleteByPrimaryKey(goodsOutOrderId);
        if (flag != null && flag == 1) {
            // 级联删除商品出库单
            GoodsOutOrderDetail goodsOutOrderDetail = new GoodsOutOrderDetail();
            goodsOutOrderDetail.setGoodsOutOrderId(goodsOutOrderId);
            this.goodsOutOrderDetailDao.delete(goodsOutOrderDetail);
            return flag;
        }
        log.error("删除商品出库单错误！");
        throw new RuntimeException("删除商品出库单错误！");
    }


    /**
     * 根据企业id查询商品出库单
     * @param page
     * @param goodsOutOrder
     * @return
     */
    @Override
    public Object selectGoodsOutOrderByPage(Page page, GoodsOutOrder goodsOutOrder) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Example example = new Example(GoodsOutOrder.class);
        example.setOrderByClause("update_time DESC, create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("companyId", goodsOutOrder.getCompanyId());
        List<GoodsOutOrder> goodsOutOrderList = this.goodsOutOrderDao.selectByExample(example);
        return ResultUtils.setData(objectPage.getTotal(), goodsOutOrderList);
    }


    /**
     * 根据商品出库单id查询商品出库单详情
     * @param page
     * @param goodsOutOrderDetail
     * @return
     */
    @Override
    public Object selectGoodsOutOrderDetailByPage(Page page, GoodsOutOrderDetail goodsOutOrderDetail) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<GoodsOutOrderDetailVO> goodsOutOrderDetailVOList = this.goodsOutOrderDetailDao.selectGoodsOutOrderDetailByPage(goodsOutOrderDetail.getGoodsOutOrderId());
        return ResultUtils.setData(objectPage.getTotal(), goodsOutOrderDetailVOList);
    }
}
