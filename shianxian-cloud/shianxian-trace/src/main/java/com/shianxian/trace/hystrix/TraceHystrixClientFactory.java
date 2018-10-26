package com.shianxian.trace.hystrix;

import com.shianxian.common.pojo.Trace;
import com.shianxian.trace.feign.TraceFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 追溯查询Hystrix降级处理
 */
@Slf4j
@Component
public class TraceHystrixClientFactory implements FallbackFactory<TraceFeignClient> {


    @Override
    public TraceFeignClient create(Throwable cause) {
        log.error("trace：追溯查询异常：{}", cause.getMessage());
        return new TraceFeignClient() {

            @Override
            public Trace selectTrace(String id) {
                Trace trace = new Trace();
                trace.setName("Hystrix降级");
                trace.setNum("111");
                return trace;
            }
        };
    }
}