package com.cpcnet.mc.selfservice.test.interfaces.provider;

import com.cpcnet.component.common.resp.ResponseResult;
import com.cpcnet.selfapi.dto.TestDto;
import com.cpcnet.selfapi.provider.test.TestApiProvider;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: feign provider
 * @author: Ebon Zheng
 * @create: 2023-08-03 10:48
 **/
@RestController
public class TestProvider implements TestApiProvider {

    @Override
    public ResponseResult<TestDto> test1(String param) {
        TestDto testDto = new TestDto();
        testDto.setParam(param);
        testDto.setResult("pass");
        return ResponseResult.success(testDto);
    }
}
