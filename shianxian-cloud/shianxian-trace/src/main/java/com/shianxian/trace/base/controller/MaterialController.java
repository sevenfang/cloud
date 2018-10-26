package com.shianxian.trace.base.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.pojo.Material;
import com.shianxian.trace.base.service.MaterialService;
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
 * @Date: 2018/9/25 13:37
 * @Description: 物料控制器
 */
@RestController
@RequestMapping("material")
@Slf4j
@Api(description = "物料控制器")
public class MaterialController {


    @Autowired
    private MaterialService materialService;


    /**
     * 保存、修改物料
     *
     * @param material
     * @param result
     * @return
     */
    @PostMapping("saveOrUpdateMaterial")
    @RequiresPermissions(value = {"base:material:save", "base:material:update"}, logical = Logical.OR)
    @ApiOperation(value="保存、修改物料接口", notes="传入id就修改，不传入id就添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "物料id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "materialName", value = "物料名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "materialType", value = "物料类型。1：原料。2：商品", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "nationCode", value = "国家标准编码", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "nationName", value = "国家标准名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "categoryId", value = "物料类别id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "isProduct", value = "是否半成品，1：是  0：不是", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "unitId", value = "计量单位id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "standard", value = "产品规格", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "quarantineNo", value = "检疫证号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "num", value = "物料库存", required = true, dataType = "int")
    })
    public ResponseEntity<Object> saveOrUpdateMaterial(@Valid Material material, BindingResult result) {
        try {
            Integer count = this.materialService.saveOrUpdateMaterial(material);
            if (count != null && count == 1 || (count == 0)) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("保存、修改物料错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 删除物料
     *
     * @param material
     * @return
     */
    @DeleteMapping("deleteMaterial")
    @RequiresPermissions("base:material:delete")
    @ApiOperation(value="删除物料接口", notes="根据id删除物料")
    @ApiImplicitParam(paramType="query", name = "id", value = "物料id", required = true, dataType = "int")
    public ResponseEntity<Object> deleteMaterial(Material material) {
        try {
            if (material.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少物料id！"));
            }
            Integer count = this.materialService.deleteMaterial(material.getId());
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除物料错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 根据企业id、物料类型分页查询物料
     *
     * @param page
     * @param material
     * @return
     */
    @GetMapping("selectMaterialByPage")
    @RequiresPermissions("base:material:select")
    @ApiOperation(value="查询物料接口", notes="根据企业id、物料类型分页查询物料")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "materialType", value = "物料类型。1：原料。2：商品", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", required = false, dataType = "int")
    })
    public ResponseEntity<Object> selectMaterialByPage(Page page, Material material) {
        try {
            if (material.getCompanyId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少企业id！"));
            }
            Object materialList = this.materialService.selectMaterialByPage(page, material);
            if (materialList != null) {
                return ResponseEntity.ok(materialList);
            }
        } catch (Exception e) {
            log.error("根据企业id、物料类型分页查询物料错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据企业id、物料类型分页查询物料错误！"));
    }


    /**
     * 根据物料id查询物料
     *
     * @param material
     * @return
     */
    @GetMapping("selectMaterialById")
    @RequiresPermissions("base:material:select")
    @ApiOperation(value="根据物料id查询物料接口", notes="根据物料id查询物料")
    @ApiImplicitParam(paramType="query", name = "id", value = "物料id", required = true, dataType = "int")
    public ResponseEntity<Object> selectMaterialById(Material material) {
        try {
            if (material.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少物料id！"));
            }
            Material m = this.materialService.selectMaterialById(material.getId());
            if (m != null) {
                return ResponseEntity.ok(ResultUtils.setData(m));
            }
        } catch (Exception e) {
            log.error("根据物料id查询物料错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据物料id查询物料错误！"));
    }

}
