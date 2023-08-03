package com.cpcnet.mc.selfservice.test.interfaces.assembler;

import com.cpcnet.component.common.utils.MappingUtils;
import com.cpcnet.mc.selfservice.test.interfaces.vo.TestVO;
import com.cpcnet.selfapi.dto.TestDto;

/**
 * @description: 转换类
 * @author: Ebon Zheng
 * @create: 2023-08-03 10:45
 **/
public class TestAssembler {

    public static TestVO dto2vo(TestDto testDto) {
        return MappingUtils.convertor(testDto, TestVO.class);
    }
}
