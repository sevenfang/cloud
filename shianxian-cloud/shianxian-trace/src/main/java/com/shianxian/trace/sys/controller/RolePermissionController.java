package com.shianxian.trace.sys.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.sys.pojo.RolePermission;
import com.shianxian.trace.sys.service.RolePermissionService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/21 17:15
 * @Description: 角色权限控制器
 */
@RestController
@RequestMapping("rolePermission")
@Slf4j
@Api(description = "角色权限控制器")
public class RolePermissionController {


    @Autowired
    private RolePermissionService rolePermissionService;


    /**
     * 保存角色所拥有的权限
     *
     * @param rolePermission
     * @return
     */
    @PostMapping("saveRolePermission")
    @RequiresPermissions("system:role:binding")
    @ApiOperation(value="保存角色所拥有的权限接口", notes="保存角色所拥有的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "roleId", value = "角色id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "permissionId", value = "权限id", required = true, dataType = "int")
    })
    public ResponseEntity<Object> saveRolePermission(@Valid RolePermission rolePermission, BindingResult result) {
        try {
            Integer count = this.rolePermissionService.saveRolePermission(rolePermission);
            if (count != null && count == 1 || (count == 0)) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("保存或修改角色所拥有的权限错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("保存或修改角色所拥有的权限错误！"));
    }


    /**
     * 删除角色所拥有的权限
     *
     * @param rolePermission
     * @param result
     * @return
     */
    @DeleteMapping("deleteRolePermission")
    @RequiresPermissions("system:role:binding")
    @ApiOperation(value="删除角色所拥有的权限接口", notes="删除角色所拥有的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "roleId", value = "角色id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "permissionId", value = "权限id", required = true, dataType = "int")
    })
    public ResponseEntity<Object> deleteRolePermission(@Valid RolePermission rolePermission, BindingResult result) {
        try {
            Integer count = this.rolePermissionService.deleteRolePermission(rolePermission);
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除角色所拥有的权限错误！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("删除角色所拥有的权限错误！"));
    }
}
