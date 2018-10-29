package com.shianxian.trace.flow.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.pojo.PurchaseInOrder;
import com.shianxian.trace.flow.pojo.PurchaseInOrderDetail;
import com.shianxian.trace.flow.service.PurchaseInOrderService;
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
 * @Date: 2018/9/27 11:03
 * @Description: 采购入库单控制器
 */
@RestController
@RequestMapping("purchaseInOrder")
@Slf4j
@Api(description = "采购入库单控制器")
public class PurchaseInOrderController {

    @Autowired
    private PurchaseInOrderService purchaseInOrderService;


    /**
     * 保存、修改采购入库单
     * @param purchaseInOrder
     * @param result
     * @return
     */
    @PostMapping("saveOrUpdatePurchaseInOrder")
    @RequiresPermissions(value = {"flow:purchaseInOrder:save", "flow:purchaseInOrder:update"}, logical = Logical.OR)
    @ApiOperation(value="保存、修改采购入库单", notes="传入id就修改，不传入id就添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "采购入库单id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "purchaseInOrderNo", value = "采购入库单号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "materialInTime", value = "原料入库日期", required = false, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "status", value = "审核状态。1：待审核，2：审核中，3：审核通过", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "id", value = "采购入库详情id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "supplierInOrderNo", value = "供应商采购入库单号，为供应商自己的进货单号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "purchaseInOrderNum", value = "采购入库的物料数量", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "materialId", value = "物料id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "categoryId", value = "物料类别id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "unitId", value = "计量单位id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "supplierId", value = "供应商id", required = false, dataType = "int")
    })
    public ResponseEntity<Object> saveOrUpdatePurchaseInOrder(@RequestBody @Valid PurchaseInOrder purchaseInOrder, BindingResult result) {
        try {
            Integer flag = this.purchaseInOrderService.saveOrUpdatePurchaseInOrder(purchaseInOrder);
            if (flag != null && flag == 1 || (flag == 0)) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("保存、修改采购入库单错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("保存、修改采购入库单错误！"));
    }


    /**
     * 删除采购入库单
     * @param purchaseInOrder
     * @return
     */
    @DeleteMapping("deletePurchaseInOrder")
    @RequiresPermissions("flow:purchaseInOrder:delete")
    @ApiOperation(value="删除采购入库单接口", notes="根据id删除采购入库单")
    @ApiImplicitParam(paramType="query", name = "id", value = "采购入库单id", required = true, dataType = "int")
    public ResponseEntity<Object> deletePurchaseInOrder(PurchaseInOrder purchaseInOrder) {
        try {
            if (purchaseInOrder.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少采购入库单id！"));
            }
            Integer flag = this.purchaseInOrderService.deletePurchaseInOrder(purchaseInOrder.getId());
            if (flag != null && flag == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除采购入库单错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("删除采购入库单错误！"));
    }


    /**
     * 根据企业id分页查询采购入库单
     * @param page
     * @param purchaseInOrder
     * @return
     */
    @GetMapping("selectPurchaseInOrderByPage")
    @RequiresPermissions("flow:purchaseInOrder:select")
    @ApiOperation(value="查询采购入库单接口", notes="根据采购入库单id查询采购入库单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", required = false, dataType = "int")
    })
    public ResponseEntity<Object> selectPurchaseInOrderByPage(Page page, PurchaseInOrder purchaseInOrder) {
        try {
            if (purchaseInOrder.getCompanyId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少企业id！"));
            }
            Object purchaseInOrderList = this.purchaseInOrderService.selectPurchaseInOrderByPage(page, purchaseInOrder);
            if (purchaseInOrderList != null) {
                return ResponseEntity.ok(purchaseInOrderList);
            }
        } catch (Exception e) {
            log.error("根据企业id分页查询采购入库单错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据企业id分页查询采购入库单错误！"));
    }


    /**
     * 根据采购入库单id查询采购入库单详情
     * @param page
     * @param purchaseInOrderDetail
     * @return
     */
    @GetMapping("selectPurchaseInOrderDetailByPage")
    @RequiresPermissions("flow:purchaseInOrder:select")
    @ApiOperation(value="查询采购入库单详情接口", notes="根据采购入库单id查询采购入库单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "purchaseInOrderId", value = "采购入库单id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", required = false, dataType = "int")
    })
    public ResponseEntity<Object> selectPurchaseInOrderDetailByPage(Page page, PurchaseInOrderDetail purchaseInOrderDetail) {
        try {
            if (purchaseInOrderDetail.getPurchaseInOrderId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少采购入库单id！"));
            }
            Object purchaseInOrderDetailVOList = this.purchaseInOrderService.selectPurchaseInOrderDetailByPage(page, purchaseInOrderDetail);
            if (purchaseInOrderDetailVOList != null) {
                return ResponseEntity.ok(purchaseInOrderDetailVOList);
            }
        } catch (Exception e) {
            log.error("根据企业id分页查询采购入库单错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据企业id分页查询采购入库单错误！"));
    }
}
