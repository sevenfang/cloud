package com.shianxian.trace.test.controller;

import com.shianxian.common.pojo.Trace;
import com.shianxian.trace.feign.TraceFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("trace")
@Api(description = "追溯查询控制器")
public class TraceController {

    @Autowired
    private TraceFeignClient traceFeignClient;

    @GetMapping("/get/{code}")
    @ApiOperation(value="查询商品信息", notes="根据追溯码查询商品信息")
    @ApiImplicitParam(paramType="query", name = "code", value = "追溯码", required = true, dataType = "string")
    public Trace get(@PathVariable String code){
         return this.traceFeignClient.selectTrace(code);
    }

    @GetMapping("/get2/{id}")
    public ResponseEntity<Trace> get2(@PathVariable Long id){
        Trace trace = new Trace();
        trace.setName("11");
        trace.setNum("22");
        return ResponseEntity.ok(trace);
    }
}
