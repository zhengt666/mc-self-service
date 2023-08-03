package com.cpcnet.selfapi.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 测试Dto
 * @author: Ebon Zheng
 * @create: 2023-08-03 10:25
 **/
@Data
public class TestDto implements Serializable {

    private String param;

    private String result;
}
