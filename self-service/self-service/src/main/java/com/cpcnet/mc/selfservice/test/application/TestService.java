package com.cpcnet.mc.selfservice.test.application;

import com.cpcnet.selfapi.dto.TestDto;
import com.cpcnet.selfapi.service.TestApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @description: 授权验证Service
 * @author: Ebon Zheng
 * @create: 2023-08-02 15:11
 **/
@Service
@RequiredArgsConstructor
public class TestService {

    private final TestApiService testService;

    public TestDto test(String param) {
        return testService.test(param);
    }
}
