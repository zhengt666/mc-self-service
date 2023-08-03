package com.cpcnet.mc.selfservice.test.interfaces.facade;

import com.cpcnet.component.common.resp.ResponseResult;
import com.cpcnet.mc.selfservice.test.application.TestService;
import com.cpcnet.mc.selfservice.test.interfaces.assembler.TestAssembler;
import com.cpcnet.mc.selfservice.test.interfaces.vo.TestVO;
import com.cpcnet.selfapi.dto.TestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description: 验证API
 * @author: Ebon Zheng
 * @create: 2023-08-02 15:05
 **/
@RestController
@RequiredArgsConstructor
@Validated
public class TestApi extends TestBaseApi {

    private final TestService authService;

    @PostMapping("/auth/{param}")
    public ResponseResult<TestVO> auth(@Valid @PathVariable("param") String param) {
        TestDto test = authService.test(param);
        return ResponseResult.success(TestAssembler.dto2vo(test));
    }
}
