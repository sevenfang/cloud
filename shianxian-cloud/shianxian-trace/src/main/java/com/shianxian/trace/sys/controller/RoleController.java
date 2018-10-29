package com.shianxian.trace.sys.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.sys.pojo.Role;
import com.shianxian.trace.sys.service.RoleService;
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
 * @Date: 2018/9/20 17:20
 * @Description: 角色控制器
 */
@RestController
@RequestMapping("role")
@Slf4j
@Api(description = "角色控制器")
public class RoleController {


    @Autowired
    private RoleService roleService;


    /**
     * 保存、修改角色
     *
     * @param role
     * @param result
     * @return
     */
    @PostMapping("saveOrUpdateRole")
    @RequiresPermissions(value = {"system:role:save", "system:role:update"}, logical = Logical.OR)
    @ApiOperation(value="保存、修改角色接口", notes="传入id就修改，不传入id就添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "角色id", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "roleName", value = "角色名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "description", value = "角色描述", dataType = "String")
    })
    public ResponseEntity<Object> saveOrUpdateRole(@Valid Role role, BindingResult result) {
        try {
            Integer count = this.roleService.saveOrUpdateRole(role);
            if (count != null && count == 1 || (count == 0)) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            } else if (count != null && count == 2) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResultUtils.setMsg("角色名已存在，不可用！"));
            }
        } catch (Exception e) {
            log.error("保存角色错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("保存角色错误！"));
    }


    /**
     * 删除角色
     *
     * @param role
     * @return
     */
    @DeleteMapping("deleteRole")
    @RequiresPermissions("system:role:delete")
    @ApiOperation(value="删除角色接口", notes="根据id删除角色")
    @ApiImplicitParam(paramType="query", name = "id", value = "角色id", required = true, dataType = "int")
    public ResponseEntity<Object> deleteRole(Role role) {
        try {
            if (role.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("缺少角色id！"));
            }
            Integer count = this.roleService.deleteRole(role.getId());
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除角色错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("删除角色错误！"));
    }


    /**
     * 分页查询角色
     *
     * @param page
     * @return
     */
    @GetMapping("selectRoleByPage")
    @RequiresPermissions("system:role:select")
    @ApiOperation(value="分页查询角色", notes="分页查询角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", dataType = "int")
    })
    public ResponseEntity<Object> selectRoleByPage(Page page) {
        try {
            Object roleList = this.roleService.selectRoleByPage(page);
            if (roleList != null) {
                return ResponseEntity.ok(roleList);
            }
        } catch (Exception e) {
            log.error("分页查询角色失败！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("分页查询角色失败！"));
    }


}
