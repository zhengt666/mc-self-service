package com.cpcnet.selfapi.provider.test;

import com.cpcnet.component.common.resp.ResponseResult;
import com.cpcnet.selfapi.dto.TestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 授权provider
 * @author: Ebon Zheng
 * @create: 2023-08-03 10:03
 **/
@Service
@FeignClient(name = "self-service", contextId = "test", path = "/self")
public interface TestApiProvider {
    @GetMapping("/auth/open-api/v1/test")
    ResponseResult<TestDto> test1(@RequestParam String param);
}
