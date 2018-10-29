package com.shianxian.trace.sys.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.sys.pojo.UserRole;
import com.shianxian.trace.sys.service.UserRoleService;
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
 * @Date: 2018/9/20 16:33
 * @Description: 用户角色控制器
 */
@RestController
@RequestMapping("userRole")
@Slf4j
@Api(description = "用户角色控制器")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;


    /**
     * 保存用户角色中间表
     *
     * @param userRole
     * @return
     */
    @PostMapping("saveUserRole")
    @RequiresPermissions("system:user:binding")
    @ApiOperation(value="保存用户角色中间表接口", notes="保存用户角色之间的关联")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "roleId", value = "角色id", required = true, dataType = "int")
    })
    public ResponseEntity<Object> saveUserRole(@Valid UserRole userRole, BindingResult result) {
        try {
            Integer count = this.userRoleService.saveUserRole(userRole);
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("保存用户角色失败！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("保存用户角色失败！"));
    }


    /**
     * 删除用户拥有的角色
     *
     * @param userRole
     * @param result
     * @return
     */
    @DeleteMapping("deleteUserRole")
    @RequiresPermissions("system:user:binding")
    @ApiOperation(value="删除用户角色中间表接口", notes="删除用户角色之间的关联")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "roleId", value = "角色id", required = true, dataType = "int")
    })
    public ResponseEntity<Object> deleteUserRole(@Valid UserRole userRole, BindingResult result) {
        try {
            Integer count = this.userRoleService.deleteUserRole(userRole);
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除用户拥有的角色失败！{}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("删除用户拥有的角色失败！"));
    }
}
