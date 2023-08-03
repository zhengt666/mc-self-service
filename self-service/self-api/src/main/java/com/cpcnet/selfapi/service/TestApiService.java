package com.cpcnet.selfapi.service;

import com.cpcnet.component.common.resp.ResponseResult;
import com.cpcnet.component.common.utils.asserts.AssertUtils;
import com.cpcnet.selfapi.dto.TestDto;
import com.cpcnet.selfapi.provider.test.TestApiProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @description: 测试service
 * @author: Ebon Zheng
 * @create: 2023-08-03 10:23
 **/
@Service
@RequiredArgsConstructor
public class TestApiService {

    private final TestApiProvider authProvider;

    public TestDto test(String param) {
        ResponseResult<TestDto> result = authProvider.test1(param);
        return AssertUtils.isSuccessElseThrow(result);
    }
}
