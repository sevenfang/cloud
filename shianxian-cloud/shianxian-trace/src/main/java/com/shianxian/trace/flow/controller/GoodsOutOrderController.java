package com.shianxian.trace.flow.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.pojo.GoodsOutOrder;
import com.shianxian.trace.flow.pojo.GoodsOutOrderDetail;
import com.shianxian.trace.flow.service.GoodsOutOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/29 14:55
 * @Description: 商品出库单控制器
 */
@RestController
@RequestMapping("goodsOutOrder")
@Slf4j
@Api(description = "商品出库单控制器")
public class GoodsOutOrderController {


    @Autowired
    private GoodsOutOrderService goodsOutOrderService;


    /**
     * 保存、修改商品出库单
     * @param goodsOutOrder
     * @param result
     * @return
     */
    @PostMapping("saveOrUpdateGoodsOutOrder")
    @RequiresPermissions(value = {"flow:goodsOutOrder:save", "flow:goodsOutOrder:update"}, logical = Logical.OR)
    @ApiOperation(value="保存、修改商品出库单接口", notes="传入id就修改，不传入id就添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "id", value = "商品出库单id", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "companyId", value = "企业id", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "goodsOutOrderNo", value = "商品出库单号", dataType = "String"),
            @ApiImplicitParam(paramType="header", name = "goodsOutOrderTime", value = "商品出库日期", dataType = "Date"),
            @ApiImplicitParam(paramType="header", name = "saleOrderId", value = "销售单id", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "saleOrderNo", value = "销售单号", dataType = "String"),
            @ApiImplicitParam(paramType="header", name = "operatorUser", value = "操作员", dataType = "String"),
            @ApiImplicitParam(paramType="header", name = "consignerUser", value = "发货人", dataType = "String"),
            @ApiImplicitParam(paramType="header", name = "id", value = "商品出库单详情id", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "materialId", value = "物料id", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "categoryId", value = "物料类别id", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "unitId", value = "计量单位id", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "goodsNum", value = "商品出库数量", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "goodsTraceCode", value = "商品追溯码", dataType = "String"),
            @ApiImplicitParam(paramType="header", name = "status", value = "订单状态。1：待审核，2：已审核，3：未发货，4：已发货，5：已收货", dataType = "int")
    })
    public ResponseEntity<Object> saveOrUpdateGoodsOutOrder(@RequestBody @Valid GoodsOutOrder goodsOutOrder, BindingResult result) {
        try {
            Integer flag = this.goodsOutOrderService.saveOrUpdateGoodsOutOrder(goodsOutOrder);
            if (flag != null && flag == 1 || (flag == 0)) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("保存、修改商品出库单错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 删除商品出库单
     * @param goodsOutOrder
     * @return
     */
    @DeleteMapping("deleteGoodsOutOrder")
    @RequiresPermissions("flow:goodsOutOrder:delete")
    @ApiOperation(value="删除商品出库单接口", notes="根据id删除商品出库单")
    @ApiImplicitParam(paramType="query", name = "id", value = "商品出库单id", required = true, dataType = "int")
    public ResponseEntity<Object> deleteGoodsOutOrder(GoodsOutOrder goodsOutOrder) {
        try {
            if (goodsOutOrder.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少商品出库单id！"));
            }
            Integer flag = this.goodsOutOrderService.deleteGoodsOutOrder(goodsOutOrder.getId());
            if (flag != null && flag == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除商品出库单错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 根据企业id查询商品出库单
     * @param goodsOutOrder
     * @return
     */
    @GetMapping("selectGoodsOutOrderByPage")
    @RequiresPermissions("flow:goodsOutOrder:select")
    @ApiOperation(value="查询商品出库单接口", notes="根据企业id查询商品出库单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", dataType = "int")
    })
    public ResponseEntity<Object> selectGoodsOutOrderByPage(Page page, GoodsOutOrder goodsOutOrder) {
        try {
            if (goodsOutOrder.getCompanyId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少企业id！"));
            }
            Object goodsOutOrderList = this.goodsOutOrderService.selectGoodsOutOrderByPage(page, goodsOutOrder);
            if (goodsOutOrderList != null) {
                return ResponseEntity.ok(goodsOutOrderList);
            }
        } catch (Exception e) {
            log.error("根据企业id查询商品出库单错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据企业id查询商品出库单错误！"));
    }


    /**
     * 根据商品出库单id查询商品出库单详情
     * @param goodsOutOrderDetail
     * @return
     */
    @GetMapping("selectGoodsOutOrderDetailByPage")
    @RequiresPermissions("flow:goodsOutOrder:select")
    @ApiOperation(value="查询商品出库单详情接口", notes="根据商品出库单id查询商品出库单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "goodsOutOrderId", value = "商品出库单id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", dataType = "int")
    })
    public ResponseEntity<Object> selectGoodsOutOrderDetailByPage(Page page, GoodsOutOrderDetail goodsOutOrderDetail) {
        try {
            if (goodsOutOrderDetail.getGoodsOutOrderId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少商品出库单id！"));
            }
            Object goodsOutOrderDetailVOList = this.goodsOutOrderService.selectGoodsOutOrderDetailByPage(page, goodsOutOrderDetail);
            if (goodsOutOrderDetailVOList != null) {
                return ResponseEntity.ok(goodsOutOrderDetailVOList);
            }
        } catch (Exception e) {
            log.error("根据商品出库单id查询商品出库单详情错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据商品出库单id查询商品出库单详情错误！"));
    }
}
