package com.shianxian.trace.flow.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.flow.pojo.ProcessMaterial;
import com.shianxian.trace.flow.pojo.ProcessMaterialDetail;
import com.shianxian.trace.flow.service.ProcessMaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/28 10:52
 * @Description: 物料加工单控制器
 */
@RestController
@RequestMapping("processMaterial")
@Slf4j
@Api(description = "物料加工单控制器")
public class ProcessMaterialController {


    @Autowired
    private ProcessMaterialService processMaterialService;


    /**
     * 添加物料加工单
     * @param processMaterial
     * @param result
     * @return
     */
    @PostMapping("saveProcessMaterial")
    @RequiresPermissions("process:material:save")
    @ApiOperation(value="添加物料加工单", notes="传入id就修改，不传入id就添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "物料加工单id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "processMaterialNo", value = "物料加工单号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "processMaterialNum", value = "物料加工数量，-1：加工库存里所有的这个原料", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "materialId", value = "物料id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "categoryId", value = "物料类别id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "unitId", value = "计量单位id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "id", value = "物料加工详情id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "processGoodsNum", value = "加工数量，加工后的商品", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "materialId", value = "物料id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "categoryId", value = "物料类别id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "unitId", value = "计量单位id", required = true, dataType = "int")
    })
    public ResponseEntity<Object> saveProcessMaterial(@RequestBody @Valid ProcessMaterial processMaterial, BindingResult result) {
        try {
            Integer flag = this.processMaterialService.saveProcessMaterial(processMaterial);
            if (flag != null && flag == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            } else if (flag != null && flag == 2) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResultUtils.setMsg("该原料库存不足！"));
            }
        } catch (Exception e) {
            log.error("添加物料加工单错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 删除物料加工单
     * @param processMaterial
     * @return
     */
    @DeleteMapping("deleteProcessMaterial")
    @RequiresPermissions("process:material:delete")
    @ApiOperation(value="删除物料加工单接口", notes="根据id删除物料加工单")
    @ApiImplicitParam(paramType="query", name = "id", value = "物料加工单id", required = true, dataType = "int")
    public ResponseEntity<Object> deleteProcessMaterial(ProcessMaterial processMaterial) {
        try {
            if (processMaterial.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少物料加工单id！"));
            }
            Integer flag = this.processMaterialService.deleteProcessMaterial(processMaterial.getId());
            if (flag != null && flag == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除物料加工单错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 根据企业id分页查询物料加工单
     * @param page
     * @param processMaterial
     * @return
     */
    @GetMapping("selectProcessMaterialByPage")
    @RequiresPermissions("process:material:select")
    @ApiOperation(value="分页查询物料加工单接口", notes="根据企业id分页查询物料加工单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", required = false, dataType = "int")
    })
    public ResponseEntity<Object> selectProcessMaterialByPage(Page page, ProcessMaterial processMaterial) {
        try {
            if (processMaterial.getCompanyId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少企业id！"));
            }
            Object processMaterialVOList = this.processMaterialService.selectProcessMaterialByPage(page, processMaterial);
            if (processMaterialVOList != null) {
                return ResponseEntity.ok(processMaterialVOList);
            }
        } catch (Exception e) {
            log.error("根据企业id分页查询物料加工单错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据企业id分页查询物料加工单错误！"));
    }


    /**
     * 根据物料加工单id分页查询物料加工单详情
     * @param page
     * @param processMaterialDetail
     * @return
     */
    @GetMapping("selectProcessMaterialDetailByPage")
    @RequiresPermissions("process:material:select")
    @ApiOperation(value="分页查询物料加工单详情", notes="根据物料加工单id分页查询物料加工单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "processMaterialId", value = "物料加工单id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", required = false, dataType = "int")
    })
    public ResponseEntity<Object> selectProcessMaterialDetailByPage(Page page, ProcessMaterialDetail processMaterialDetail) {
        try {
            if (processMaterialDetail.getProcessMaterialId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少物料加工单id！"));
            }
            Object processMaterialDetailVOList = this.processMaterialService.selectProcessMaterialDetailByPage(page, processMaterialDetail);
            if (processMaterialDetailVOList != null) {
                return ResponseEntity.ok(processMaterialDetailVOList);
            }
        } catch (Exception e) {
            log.error("根据物料加工单id分页查询物料加工单详情错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据物料加工单id分页查询物料加工单详情错误！"));
    }
}
