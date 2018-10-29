package com.shianxian.trace.flow.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.pojo.RawOutOrder;
import com.shianxian.trace.flow.service.RawOutOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 15:46
 * @Description: 原料出库单控制器
 */
@RestController
@RequestMapping("rawOutOrder")
@Slf4j
@Api(description = "原料出库单控制器")
public class RawOutOrderController {


    @Autowired
    private RawOutOrderService rawOutOrderService;


    /**
     * 根据企业id分页查询原料出库单
     * @param page
     * @param rawOutOrder
     * @return
     */
    @GetMapping("selectRawOutOrderByPage")
    @RequiresPermissions("flow:rawOutOrder:select")
    @ApiOperation(value="分页查询原料出库单接口", notes="根据企业id分页查询原料出库单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", dataType = "int")
    })
    public ResponseEntity<Object> selectRawOutOrderByPage(Page page, RawOutOrder rawOutOrder) {
        try {
            if (rawOutOrder.getCompanyId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少企业id！"));
            }
            Object rawOutOrderVOList = this.rawOutOrderService.selectRawOutOrderByPage(page, rawOutOrder);
            if (rawOutOrderVOList != null) {
                return ResponseEntity.ok(rawOutOrderVOList);
            }
        } catch (Exception e) {
            log.error("根据企业id分页查询原料出库单错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据企业id分页查询原料出库单错误！"));
    }


    /**
     * 根据id改变原料出库单审核状态
     * @param rawOutOrder
     * @return
     */
    @PutMapping("checkRawOutOrderById")
    @RequiresPermissions("flow:rawOutOrder:update")
    @ApiOperation(value="改变原料出库单审核状态接口", notes="根据id改变原料出库单审核状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "原料出库单id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "status", value = "原料出库单状态。审核状态。1：待审核，2：审核不过，3：审核通过。默认是审核通过", required = true, dataType = "int")
    })
    public ResponseEntity<Object> checkRawOutOrderById(RawOutOrder rawOutOrder) {
        try {
            if (rawOutOrder.getId() == null || rawOutOrder.getStatus() == null || rawOutOrder.getProcessMaterialNum() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少原料出库单id、状态、数量！"));
            }
            Integer flag = this.rawOutOrderService.checkRawOutOrderById(rawOutOrder);
            if (flag != null && flag == 1 || (flag == 0)) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("根据id改变原料出库单审核状态！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }
}
