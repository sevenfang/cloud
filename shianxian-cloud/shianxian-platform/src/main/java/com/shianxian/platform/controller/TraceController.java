package com.shianxian.platform.controller;

import com.shianxian.common.pojo.Trace;
import com.shianxian.common.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: 赵明明
 * @Date: 2018/10/19 16:58
 * @Description: 追溯查询
 */
@RestController
@Slf4j
@RequestMapping("trace")
@Api(description = "追溯查询控制器")
public class TraceController {


    /**
     * 根据追溯码查询商品信息
     *
     * @param code 追溯码
     * @return
     */
    @GetMapping("selectTrace/{code}")
    @ApiOperation(value = "查询商品信息", notes = "根据追溯码查询商品信息")
    @ApiImplicitParam(paramType = "query", name = "code", value = "追溯码", required = true, dataType = "string")
    public ResponseEntity<Object> selectTrace(@PathVariable("code") String code) {
        try {
            Trace trace = new Trace();
            trace.setName("11");
            trace.setNum("22");
            return ResponseEntity.ok(trace);
        } catch (Exception e) {
            log.error("根据追溯码查询商品信息错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.setMsg("根据追溯码查询商品信息错误！"));
    }
}
