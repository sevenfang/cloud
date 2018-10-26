package com.shianxian.trace.feign;

import com.shianxian.common.pojo.Trace;
import com.shianxian.trace.hystrix.TraceHystrixClientFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 追溯查询
 */
@FeignClient(value = "shianxian-platform", fallbackFactory = TraceHystrixClientFactory.class)
public interface TraceFeignClient {

  @GetMapping (value = "trace/selectTrace/{code}")
  Trace selectTrace(@PathVariable("code") String id);

}
