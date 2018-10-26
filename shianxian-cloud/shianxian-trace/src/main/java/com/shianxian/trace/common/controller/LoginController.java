package com.shianxian.trace.common.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.sys.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/18 17:32
 * @Description: 登录
 */
@Controller
@Slf4j
@Api(description = "登录控制器")
public class LoginController {


    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping("/login")
    @ApiOperation(value = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String")
    })
    public ResponseEntity<Object> login(User user) {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return ResponseEntity.badRequest().body("用户名或密码不能为空！");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            User loginUser = (User)  subject.getPrincipal();
            return ResponseEntity.ok().body(loginUser);
        } catch (LockedAccountException lae) {
            token.clear();
            log.error("用户已经被锁定不能登录，请与管理员联系！", lae);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResultUtils.setMsg("用户已经被锁定不能登录，请与管理员联系！"));
        } catch (AuthenticationException e) {
            token.clear();
            log.error("用户或密码不正确！", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResultUtils.setMsg("用户或密码不正确！"));
        }
    }


    /**
     * 退出登录
     *
     * @return
     */
    @GetMapping("/logout")
    @ApiOperation(value = "退出登录接口")
    public ResponseEntity<Object> dlogin() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseEntity.ok().body(ResultUtils.setMsg("退出登录成功！"));
    }

}
