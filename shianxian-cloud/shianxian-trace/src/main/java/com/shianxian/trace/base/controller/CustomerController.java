package com.shianxian.trace.base.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.pojo.Customer;
import com.shianxian.trace.base.service.CustomerService;
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
 * @Date: 2018/9/26 11:15
 * @Description: 客户控制器
 */
@RestController
@RequestMapping("customer")
@Slf4j
@Api(description = "客户控制器")
public class CustomerController {


    @Autowired
    private CustomerService customerService;


    /**
     * 保存、修改客户
     *
     * @param customer
     * @param result
     * @return
     */
    @PostMapping("saveOrUpdateCustomer")
    @RequiresPermissions(value = {"base:customer:save", "base:customer:update"}, logical = Logical.OR)
    @ApiOperation(value="保存、修改客户接口", notes="传入id就修改，不传入id就添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "客户id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "customerName", value = "客户名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "contacts", value = "联系人", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "tel", value = "联系电话", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "address", value = "经营地址", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "customerCode", value = "追溯节点码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "businessLicense", value = "营业执照/证件号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "enable", value = "1：有效  0：无效。默认有效", required = false, dataType = "int")
    })
    public ResponseEntity<Object> saveOrUpdateCustomer(@Valid Customer customer, BindingResult result) {
        try {
            Integer count = this.customerService.saveOrUpdateCustomer(customer);
            if (count != null && count == 1 || (count == 0)) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("保存、修改客户错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 删除客户
     *
     * @param customer
     * @return
     */
    @DeleteMapping("deleteCustomer")
    @RequiresPermissions("base:customer:delete")
    @ApiOperation(value="删除客户", notes="根据id删除客户")
    @ApiImplicitParam(paramType="query", name = "id", value = "客户id", required = true, dataType = "int")
    public ResponseEntity<Object> deleteCustomer(Customer customer) {
        try {
            if (customer.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少客户id！"));
            }
            Integer count = this.customerService.deleteCustomer(customer.getId());
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除客户错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 根据企业id分页查询客户
     *
     * @param customer
     * @return
     */
    @GetMapping("selectCustomerByPage")
    @RequiresPermissions("base:customer:select")
    @ApiOperation(value="分页查询客户", notes="根据企业id分页查询客户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", required = false, dataType = "int")
    })
    public ResponseEntity<Object> selectCustomerByPage(Page page, Customer customer) {
        try {
            if (customer.getCompanyId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少企业id！"));
            }
            Object customerList = this.customerService.selectCustomerByPage(page, customer);
            if (customerList != null) {
                return ResponseEntity.ok(customerList);
            }
        } catch (Exception e) {
            log.error("根据企业id分页查询客户错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据企业id分页查询客户错误！"));
    }
}
