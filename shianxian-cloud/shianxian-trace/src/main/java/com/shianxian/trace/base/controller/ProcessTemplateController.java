package com.shianxian.trace.base.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.pojo.ProcessTemplate;
import com.shianxian.trace.base.pojo.ProcessTemplateDetail;
import com.shianxian.trace.base.service.ProcessTemplateService;
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
 * @Date: 2018/9/26 13:37
 * @Description: 加工模板控制器
 */
@RestController
@RequestMapping("processTemplate")
@Slf4j
@Api(description = "加工模板控制器")
public class ProcessTemplateController {


    @Autowired
    private ProcessTemplateService processTemplateService;


    /**
     * 保存、修改加工模板
     *
     * @param processTemplate
     * @param result
     * @return
     */
    @PostMapping("saveOrUpdateProcessTemplate")
    @RequiresPermissions(value = {"base:processTemplate:save", "base:processTemplate:update"}, logical = Logical.OR)
    @ApiOperation(value="保存、修改加工模板接口", notes="以json格式提交，传入id就修改，不传入id就添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "id", value = "加工模板id", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "companyId", value = "企业id", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "materialId", value = "物料id，此物料为原料", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "processTemplateName", value = "加工模板名称", dataType = "String"),
            @ApiImplicitParam(paramType="header", name = "processTemplateDetails", value = "加工模板详情集合", dataType = "String"),
            @ApiImplicitParam(paramType="header", name = "id", value = "加工模板明细id", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "categoryId", value = "物料类别id", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "unitId", value = "计量单位id", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "materialId", value = "物料id，此物料为加工过后的商品", dataType = "int"),
            @ApiImplicitParam(paramType="header", name = "processTemplateNum", value = "加工模板数量", dataType = "int")
    })
    public ResponseEntity<Object> saveOrUpdateProcessTemplate(@RequestBody @Valid ProcessTemplate processTemplate, BindingResult result) {
        try {
            Integer count = this.processTemplateService.saveOrUpdateProcessTemplate(processTemplate);
            if (count != null && count == 1 || (count == 0)) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("保存、修改加工模板错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 删除加工模板
     *
     * @param processTemplate
     * @return
     */
    @DeleteMapping("deleteProcessTemplate")
    @RequiresPermissions("base:processTemplate:delete")
    @ApiOperation(value="删除加工模板接口", notes="根据id删除加工模板")
    @ApiImplicitParam(paramType="query", name = "id", value = "加工模板", required = true, dataType = "int")
    public ResponseEntity<Object> deleteProcessTemplate(ProcessTemplate processTemplate) {
        try {
            Integer count = this.processTemplateService.deleteProcessTemplate(processTemplate.getId());
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除加工模板错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 根据企业id分页查询模板
     * @param page
     * @param processTemplate
     * @return
     */
    @GetMapping("selectProcessTemplateByPage")
    @RequiresPermissions("base:processTemplate:select")
    @ApiOperation(value="分页查询模板", notes="根据企业id分页查询模板")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", dataType = "int")
    })
    public ResponseEntity<Object> selectProcessTemplateByPage(Page page, ProcessTemplate processTemplate) {
        try {
            if (processTemplate.getCompanyId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少企业id！"));
            }
            Object processTemplateList = this.processTemplateService.selectProcessTemplateByPage(page, processTemplate);
            if (processTemplateList != null) {
                return ResponseEntity.ok(processTemplateList);
            }
        } catch (Exception e) {
            log.error("根据企业id分页查询模板错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据企业id分页查询模板错误！"));
    }


    /**
     * 根据模板id分页查询模板详情
     * @param page
     * @param processTemplateDetail
     * @return
     */
    @GetMapping("selectProcessTemplateItemByPage")
    @RequiresPermissions("base:processTemplate:select")
    @ApiOperation(value="分页查询模板详情", notes="根据模板id分页查询模板详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "processTemplateId", value = "加工模板id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", dataType = "int")
    })
    public ResponseEntity<Object> selectProcessTemplateItemByPage(Page page, ProcessTemplateDetail processTemplateDetail) {
        try {
            if (processTemplateDetail.getProcessTemplateId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少加工模板id！"));
            }
            Object materialVOList = this.processTemplateService.selectProcessTemplateItemByPage(page, processTemplateDetail);
            if (materialVOList != null) {
                return ResponseEntity.ok(materialVOList);
            }
        } catch (Exception e) {
            log.error("根据模板id分页查询模板详情错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据模板id分页查询模板详情错误！"));
    }
}
