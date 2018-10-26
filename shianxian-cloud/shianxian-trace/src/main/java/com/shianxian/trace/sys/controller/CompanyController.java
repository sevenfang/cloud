package com.shianxian.trace.sys.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.sys.pojo.Company;
import com.shianxian.trace.sys.pojo.User;
import com.shianxian.trace.sys.service.CompanyService;
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
 * @Date: 2018/9/20 10:58
 * @Description: 企业控制器
 */
@RestController()
@RequestMapping("company")
@Slf4j
@Api(description = "企业控制器")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    /**
     * 保存、修改企业
     *
     * @param company
     * @param result
     * @return
     */
    @PostMapping("saveOrUpdateCompany")
    @RequiresPermissions(value = {"system:company:save", "system:company:update"}, logical = Logical.OR)
    @ApiOperation(value="保存、修改企业接口", notes="传入id就修改，不传入id就添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "企业id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "name", value = "企业名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "shortName", value = "企业简称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "companyCode", value = "企业节点码", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "traceCode", value = "追溯节点码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "address", value = "经营地址", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "code", value = "统一社会代码/证件号码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "corporate", value = "法人代表", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "contacts", value = "联系人", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "tel", value = "联系电话", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "companyType", value = "企业类型", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "territory", value = "经营领域", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "business", value = "主营业务", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "scope", value = "经营范围", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "province", value = "省", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "city", value = "市", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "area", value = "区", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "enable", value = "是否有效  1：有效  0：无效。默认有效", required = false, dataType = "int")
    })
    public ResponseEntity<Object> saveOrUpdateCompany(@Valid Company company, BindingResult result) {
        try {
            Integer count = this.companyService.saveOrUpdateCompany(company);
            if (count != null && count == 1 || (count == 0)) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("保存、修改企业错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("保存、修改企业错误！"));
    }


    /**
     * 根据用户id查询企业信息
     *
     * @param user
     * @return
     */
    @GetMapping("selectByUserId")
    @RequiresPermissions("system:company:select")
    @ApiOperation(value="查询企业信息", notes="根据用户id查询企业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "用户id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", required = false, dataType = "int")
    })
    public ResponseEntity<Object> selectByUserId(Page page, User user) {
        if (user.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少用户id！"));
        }
        return this.companyService.selectByUserId(page, user.getId());
    }


    /**
     * 删除企业
     *
     * @param company
     * @return
     */
    @DeleteMapping("deleteCompany")
    @RequiresPermissions("system:company:delete")
    @ApiOperation(value="删除企业接口", notes="根据id删除企业")
    @ApiImplicitParam(paramType="query", name = "id", value = "企业id", required = true, dataType = "int")
    public ResponseEntity<Object> deleteCompany(Company company) {
        try {
            if (company.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("企业id为空！"));
            }
            Integer count = this.companyService.deleteCompany(company.getId());
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除企业错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("删除企业错误！"));
    }
}
