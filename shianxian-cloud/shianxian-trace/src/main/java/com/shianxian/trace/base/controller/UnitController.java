package com.shianxian.trace.base.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.pojo.Unit;
import com.shianxian.trace.base.service.UnitService;
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
 * @Date: 2018/9/25 11:04
 * @Description: 计量单位控制器
 */
@RestController
@RequestMapping("unit")
@Slf4j
@Api(description = "计量单位控制器")
public class UnitController {


    @Autowired
    private UnitService unitService;


    /**
     * 保存、修改计量单位
     *
     * @param unit
     * @param result
     * @return
     */
    @PostMapping("saveOrUpdateUnit")
    @RequiresPermissions(value = {"base:unit:save", "base:unit:update"}, logical = Logical.OR)
    @ApiOperation(value="保存、修改加工模板接口", notes="传入id就修改，不传入id就添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "计量单位id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "unitName", value = "计量单位名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "description", value = "计量单位描述", required = false, dataType = "String")
    })
    public ResponseEntity<Object> saveOrUpdateUnit(@Valid Unit unit, BindingResult result) {
        try {
            Integer count = this.unitService.saveOrUpdateUnit(unit);
            if (count != null && count == 1 || (count == 0)) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("保存、修改计量单位错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("保存、修改计量单位错误！"));
    }


    /**
     * 删除计量单位
     *
     * @param unit
     * @return
     */
    @DeleteMapping("deleteUnit")
    @RequiresPermissions("base:unit:delete")
    @ApiOperation(value="删除计量单位接口", notes="根据id删除计量单位")
    @ApiImplicitParam(paramType="query", name = "id", value = "计量单位id", required = true, dataType = "int")
    public ResponseEntity<Object> deleteUnit(Unit unit) {
        try {
            if (unit.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少计量单位id！"));
            }
            Integer count = this.unitService.deleteUnit(unit.getId());
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除计量单位失败！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 根据企业id分页查询计量单位
     *
     * @param page
     * @param unit
     * @return
     */
    @GetMapping("selectUnitByPage")
    @RequiresPermissions("base:unit:select")
    @ApiOperation(value="分页查询计量单位", notes="根据企业id分页查询计量单位")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", required = false, dataType = "int")
    })
    public ResponseEntity<Object> selectUnitByPage(Page page, Unit unit) {
        try {
            if (unit.getCompanyId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少企业id！"));
            }
            Object unitList = this.unitService.selectUnitByPage(page, unit);
            if (unitList != null) {
                return ResponseEntity.ok(unitList);
            }
        } catch (Exception e) {
            log.error("根据企业id分页查询计量单位错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据企业id分页查询计量单位错误！"));
    }



    /**
     * 根据计量单位id查询计量单位
     *
     * @param unit
     * @return
     */
    @GetMapping("selectUnitById")
    @RequiresPermissions("base:unit:select")
    @ApiOperation(value="根据计量单位id查询计量单位接口", notes="根据计量单位id查询计量单位")
    @ApiImplicitParam(paramType="query", name = "id", value = "计量单位id", required = true, dataType = "int")
    public ResponseEntity<Object> selectUnitById(Unit unit) {
        try {
            if (unit.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少计量单位id！"));
            }
            Unit u = this.unitService.selectUnitById(unit.getId());
            if (u != null) {
                return ResponseEntity.ok(ResultUtils.setData(u));
            }
        } catch (Exception e) {
            log.error("根据计量单位id查询计量单位错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据计量单位id查询计量单位错误！"));
    }

}
