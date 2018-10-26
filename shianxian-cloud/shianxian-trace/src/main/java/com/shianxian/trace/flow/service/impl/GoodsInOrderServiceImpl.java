package com.shianxian.trace.flow.service.impl;

import com.github.pagehelper.PageHelper;
import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.dao.GoodsInOrderDao;
import com.shianxian.trace.flow.pojo.GoodsInOrder;
import com.shianxian.trace.flow.service.GoodsInOrderService;
import com.shianxian.trace.flow.vo.GoodsInOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 16:21
 * @Description: 商品入库单业务层实现
 */
@Service
public class GoodsInOrderServiceImpl implements GoodsInOrderService {


    @Autowired
    private GoodsInOrderDao goodsInOrderDao;


    /**
     * 根据企业id分页查询商品入库单
     *
     * @param page
     * @param goodsInOrder
     * @return
     */
    @Override
    public Object selectgGodsInOrderByPage(Page page, GoodsInOrder goodsInOrder) {
        com.github.pagehelper.Page<Object> objectPage = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<GoodsInOrderVO> goodsInOrderVOList = this.goodsInOrderDao.selectgGodsInOrderByPage(goodsInOrder.getCompanyId());
        return ResultUtils.setData(objectPage.getTotal(), goodsInOrderVOList);
    }


    /**
     * 根据id改变商品入库单审核状态
     *
     * @param goodsInOrder
     * @return
     */
    @Transactional
    @Override
    public Integer checkGoodsInOrderById(GoodsInOrder goodsInOrder) {
        return this.goodsInOrderDao.updateByPrimaryKeySelective(goodsInOrder);
    }
}
