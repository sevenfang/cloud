package com.shianxian.trace.sys.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.common.pojo.Page;
import com.shianxian.trace.sys.pojo.User;
import com.shianxian.trace.sys.service.UserService;
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
 * @Date: 2018/9/17 14:57
 * @Description: 用户控制层
 */
@RestController
@RequestMapping("user")
@Slf4j
@Api(description = "用户控制器")
public class UserController {


    @Autowired
    private UserService userService;


    /**
     * 分页查询用户
     *
     * @param page
     * @return
     */
    @GetMapping("/selectUserByPage")
    @RequiresPermissions("system:user:select")
    @ApiOperation(value="分页查询用户接口", notes="根据用户权限分页查询用户（超级管理员可以查询所有用户，管理员则查询除了超级管理员之外的所有用户，普通用户只能看见自己的子用户）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "要查看的页码，默认是1", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页查询数量，默认是10", required = false, dataType = "int")
    })
    public ResponseEntity<Object> selectUserByPage(Page page) {
        try {
            Object userList = this.userService.selectUserByPage(page);
            if (userList != null) {
                return ResponseEntity.ok(userList);
            }
        } catch (Exception e) {
            log.error("分页查询用户错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("分页查询用户错误！"));
    }


    /**
     * 根据id修改用户信息
     *
     * @return
     */
    @PutMapping("/updateUserById")
    @RequiresPermissions("system:user:update")
    @ApiOperation(value="修改用户信息接口", notes="根据id修改用户信息")
    @ApiImplicitParam(paramType="query", name = "id", value = "用户id", required = false, dataType = "int")
    public ResponseEntity<Object> updateUserById(User user) {
        try {
            if (user.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("没有这个用户id！"));
            }
            Integer count = this.userService.updateUserById(user);
            if (count != null && count == 1 || (count == 0)) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("根据id修改用户信息错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping("/saveUser")
    @RequiresPermissions("system:user:save")
    @ApiOperation(value="保存用户接口", notes="保存用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "nickName", value = "昵称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "companyId", value = "所属企业id", required = false, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "isAdmin", value = "是否为管理员  1：有效  0：无效", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "enable", value = "是否有效  1：有效  0：无效。默认有效", required = false, dataType = "int")
    })
    public ResponseEntity<Object> saveUser(@Valid User user, BindingResult result) {
        try {
            Integer count = this.userService.saveUser(user);
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            } else if (count == 2) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResultUtils.setMsg("用户名已存在，不可用！"));
            }
        } catch (Exception e) {
            log.error("保存用户信息错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }


    /**
     * 删除用户
     * @param user
     * @return
     */
    @DeleteMapping("deleteUser")
    @RequiresPermissions("system:user:delete")
    @ApiOperation(value="删除用户接口", notes="根据id删除用户")
    @ApiImplicitParam(paramType="query", name = "id", value = "用户id", required = true, dataType = "int")
    public ResponseEntity<Object> deleteUser(User user) {
        try {
            if (user.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("没有用户id！"));
            }
            if (user.getId() == 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("超级管理员不允许删除！"));
            }
            Integer count = this.userService.deleteUser(user.getId());
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("删除用户错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("删除用户错误！"));
    }


    /**
     * 修改密码
     * @param user
     * @return
     */
    @PutMapping("updatePassword")
    @RequiresPermissions("system:user:updatePwd")
    @ApiOperation(value="修改用户密码接口", notes="修改用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "用户id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "密码", required = true, dataType = "String")
    })
    public ResponseEntity<Object> updatePassword(User user) {
        try {
            if (user.getId() == null || user.getUsername() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("没有用户id或者用户名！"));
            }
            if (user.getPassword() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("请输入密码！"));
            }
            Integer count = this.userService.updatePassword(user);
            if (count != null && count == 1) {
                return ResponseEntity.ok(ResultUtils.successMsg());
            }
        } catch (Exception e) {
            log.error("修改密码错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("修改密码错误！"));
    }

}
