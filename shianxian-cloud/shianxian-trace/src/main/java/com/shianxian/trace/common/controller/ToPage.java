package com.shianxian.trace.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/19 9:23
 * @Description: 通用页面跳转
 */
@Controller
@RequestMapping("view")
@Api(description = "通用页面跳转控制器")
public class ToPage {

    /**
     * 通用页面跳转
     * @param page 页面
     * @return
     */
    @RequestMapping("{page}")
    @ApiOperation(value="通用页面跳转接口")
    @ApiImplicitParam(paramType="query", name = "page", value = "页面名称", required = true, dataType = "String")
    public String toView(@PathVariable("page") String page) {
        return page;
    }

}
