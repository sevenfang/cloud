package com.shianxian.trace.flow.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.pojo.SaleOrder;
import com.shianxian.trace.flow.pojo.SaleOrderDetail;
import com.shianxian.trace.flow.service.SaleOrderService;
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
 * @Date: 2018/9/29 11:00
 * @Description: 销售单控制器
 */
@RestController
@RequestMapping("saleOrder")
@Slf4j
@Api(description = "销售单控制器")
public class SaleOrderController {


    @Autowired
    private SaleOrderService saleOrderService;


    /**
     * 保存、修改销售单
     * @param saleOrder
     * @param result
     * @return
     */
    @PostMapping("saveOrUpdateSaleOrder")
    @RequiresPermissions(value = {"flow:saleOrder:save" , "flow:saleOrder:update"}, logical = Logical.OR)
    @ApiOperation(value="保存、修改销售单", notes="传入id就修改，不传入id就添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "销售单id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "saleOrderNo", value = "销售单号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "saleOrderTime", value = "销售单日期", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "id", value = "销售单详情id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "saleOrderId", value = "销售单id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "materialId", value = "物料id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "categoryId", value = "物料类别id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "unitId", value = "计量单位id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "saleNum", value = "销售物料数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "status", value = "订单状态。1：待审核，2：已审核，3：未发货，4：已发货，5：已收货", required = true, dataType = "int")
    })
    public ResponseEntity<Object> saveOrUpdateSaleOrder(@RequestBody @Valid SaleOrder saleOrder, BindingResult result) {
        try {
            Integer flag = this.saleOrderService.saveOrUpdateSaleOrder(saleOrder);
            if (flag != null && flag == 1 || (flag == 0)) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("保存、修改销售单错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 删除销售单
     * @param saleOrder
     * @return
     */
    @DeleteMapping("deleteSaleOrder")
    @RequiresPermissions("flow:saleOrder:delete")
    @ApiOperation(value="删除销售单接口", notes="根据id删除销售单")
    @ApiImplicitParam(paramType="query", name = "id", value = "id", required = true, dataType = "int")
    public ResponseEntity<Object> deleteSaleOrder(SaleOrder saleOrder) {
        try {
            if (saleOrder.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少销售单id！"));
            }
            Integer flag = this.saleOrderService.deleteSaleOrder(saleOrder.getId());
            if (flag != null && flag == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除销售单错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 根据企业id分页查询销售单
     * @param saleOrder
     * @return
     */
    @GetMapping("selectSaleOrderByPage")
    @RequiresPermissions("flow:saleOrder:select")
    @ApiOperation(value="分页查询销售单接口", notes="根据企业id分页查询销售单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", required = false, dataType = "int")
    })
    public ResponseEntity<Object> selectSaleOrderByPage(Page page, SaleOrder saleOrder) {
        try {
            if (saleOrder.getCompanyId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少企业id！"));
            }
            Object saleOrderList = this.saleOrderService.selectSaleOrderByPage(page, saleOrder);
            if (saleOrderList != null) {
                return ResponseEntity.ok(saleOrderList);
            }
        } catch (Exception e) {
            log.error("根据企业id分页查询销售单错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据企业id分页查询销售单错误！"));
    }


    /**
     * 根据销售单id分页查询销售单详情
     * @param saleOrderDetail
     * @return
     */
    @GetMapping("selectSaleOrderDetailByPage")
    @RequiresPermissions("flow:saleOrder:select")
    @ApiOperation(value="分页查询销售单详情接口", notes="根据销售单id分页查询销售单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "saleOrderId", value = "销售单id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", required = false, dataType = "int")
    })
    public ResponseEntity<Object> selectSaleOrderDetailByPage(Page page, SaleOrderDetail saleOrderDetail) {
        try {
            if (saleOrderDetail.getSaleOrderId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少销售单id！"));
            }
            Object saleOrderDetailVOList = this.saleOrderService.selectSaleOrderDetailByPage(page, saleOrderDetail);
            if (saleOrderDetailVOList != null) {
                return ResponseEntity.ok(saleOrderDetailVOList);
            }
        } catch (Exception e) {
            log.error("根据销售单id分页查询销售单详情错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据销售单id分页查询销售单详情错误！"));
    }
}
