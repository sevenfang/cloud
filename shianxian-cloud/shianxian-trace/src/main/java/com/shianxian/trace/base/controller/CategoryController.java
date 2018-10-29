package com.shianxian.trace.base.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.base.pojo.Category;
import com.shianxian.trace.base.service.CategoryService;
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
 * @Date: 2018/9/25 10:22
 * @Description: 物料类别控制器
 */
@RestController
@RequestMapping("category")
@Slf4j
@Api(description = "物料类别控制器")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;


    /**
     * 保存或修改物料类别
     *
     * @param category
     * @param result
     * @return
     */
    @PostMapping("saveOrUpdateCategory")
    @RequiresPermissions(value = {"base:category:save", "base:category:update"}, logical = Logical.OR)
    @ApiOperation(value = "保存、修改物料类别接口", notes = "传入id就修改，不传入id就添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "物料类别id", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "categoryName", value = "物料类别名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "categoryType", value = "物料类别分类", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "description", value = "物料类别描述", dataType = "String")
    })
    public ResponseEntity<Object> saveOrUpdateCategory(@Valid Category category, BindingResult result) {
        try {
            Integer count = this.categoryService.saveOrUpdateCategory(category);
            if (count != null && count == 1 || (count == 0)) {
                int i = 0/0;
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("保存或修改物料类别错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 删除物料类别
     *
     * @param category
     * @return
     */
    @DeleteMapping("deleteCategory")
    @RequiresPermissions("base:category:delete")
    @ApiOperation(value = "删除物料类别接口", notes = "根据id删除物料类别")
    @ApiImplicitParam(paramType = "query", name = "id", value = "物料类别id", required = true, dataType = "int")
    public ResponseEntity<Object> deleteCategory(Category category) {
        try {
            if (category.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("没有物料类别id！"));
            }
            Integer count = this.categoryService.deleteCategory(category.getId());
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除物料类别类别错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 根据企业id分页查询物料类别
     *
     * @param category
     * @return
     */
    @GetMapping("selectCategoryByPage")
    @RequiresPermissions("base:category:select")
    @ApiOperation(value = "查询物料类别接口", notes = "根据企业id分页查询物料类别")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "companyId", value = "企业id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "categoryType", value = "类别分类。1为原料，2为商品", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "要查看的页码，默认是1", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页查询数量，默认是10", dataType = "int")
    })
    public ResponseEntity<Object> selectCategoryByPage(Page page, Category category) {
        try {
            if (category.getCompanyId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少企业id！"));
            }
            Object categoryList = this.categoryService.selectCategoryByPage(page, category);
            if (categoryList != null) {
                return ResponseEntity.ok(categoryList);
            }
        } catch (Exception e) {
            log.error("根据企业id分页查询物料类别错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据企业id分页查询物料类别错误！"));
    }


    /**
     * 根据物料类别id查询物料类别
     *
     * @param category
     * @return
     */
    @GetMapping("selectCategoryById")
    @RequiresPermissions("base:category:select")
    @ApiOperation(value = "根据物料类别id查询物料类别接口", notes = "根据物料类别id查询物料类别")
    @ApiImplicitParam(paramType = "query", name = "id", value = "物料类别id", required = true, dataType = "int")
    public ResponseEntity<Object> selectCategoryById(Category category) {
        try {
            if (category.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少物料类别id！"));
            }
            Category c = this.categoryService.selectCategoryById(category.getId());
            if (c != null) {
                return ResponseEntity.ok(ResultUtils.setData(c));
            }
        } catch (Exception e) {
            log.error("根据物料类别id查询物料类别错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据物料类别id查询物料类别错误！"));
    }
}
