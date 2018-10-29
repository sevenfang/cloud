package com.shianxian.trace.base.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.pojo.Supplier;
import com.shianxian.trace.base.service.SupplierService;
import com.shianxian.trace.common.pojo.Page;
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
 * @Date: 2018/9/26 10:40
 * @Description: 供应商控制器
 */
@RestController
@RequestMapping("supplier")
@Slf4j
@Api(description = "供应商控制器")
public class SupplierController {


    @Autowired
    private SupplierService supplierService;


    /**
     * 保存、修改供应商
     *
     * @param supplier
     * @param result
     * @return
     */
    @PostMapping("saveOrUpdateSupplier")
    @RequiresPermissions(value = {"base:supplier:save", "base:supplier:update"}, logical = Logical.OR)
    @ApiOperation(value="保存、修改加工模板接口", notes="传入id就修改，不传入id就添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "供应商id", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "supplierName", value = "供应商名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "contacts", value = "联系人", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "tel", value = "联系电话", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "address", value = "经营地址", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "businessLicense", value = "营业执照/证件号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "intro", value = "简介", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "enable", value = "是否有效  1：有效  0：无效。默认有效", dataType = "int")
    })
    public ResponseEntity<Object> saveOrUpdateSupplier(@Valid Supplier supplier, BindingResult result) {
        try {
            Integer count = this.supplierService.saveOrUpdateSupplier(supplier);
            if (count != null && count == 1 || (count == 0)) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("保存、修改供应商错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 删除供应商
     *
     * @param supplier
     * @return
     */
    @DeleteMapping("deleteSupplier")
    @RequiresPermissions("base:supplier:delete")
    @ApiOperation(value="删除供应商接口", notes="根据id删除供应商")
    @ApiImplicitParam(paramType="query", name = "id", value = "供应商id", required = true, dataType = "int")
    public ResponseEntity<Object> deleteSupplier(Supplier supplier) {
        try {
            if (supplier.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少供应商id！"));
            }
            Integer count = this.supplierService.deleteSupplier(supplier.getId());
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除供应商错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 根据企业id分页查询供应商
     *
     * @param page
     * @param supplier
     * @return
     */
    @GetMapping("selectSupplierByPage")
    @RequiresPermissions("base:supplier:select")
    @ApiOperation(value="分页查询供应商", notes="根据企业id分页查询供应商")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", dataType = "int")
    })
    public ResponseEntity<Object> selectSupplierByPage(Page page, Supplier supplier) {
        try {
            if (supplier.getCompanyId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少企业id！"));
            }
            Object supplierList = this.supplierService.selectSupplierByPage(page, supplier);
            if (supplierList != null) {
                return ResponseEntity.ok(supplierList);
            }
        } catch (Exception e) {
            log.error("根据企业id分页查询供应商错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据企业id分页查询供应商错误！"));
    }
}
